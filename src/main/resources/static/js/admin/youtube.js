const videoId = 'eQNHDV7lKgE';
const tag = document.createElement('script');
tag.src = 'https://www.youtube.com/iframe_api';
const firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

const magId = document.querySelector('.magId').value;

// YouTube API 로드 후 실행될 함수 정의
let player;
let intervalId;
let maxPosition = 0; // 최대 재생 위치 초기화
let fnlPosition = 0; // 최종 재생 위치 초기화
function onYouTubeIframeAPIReady() {
    player = new YT.Player('player', {
        height: '500',
        width: '900',
        videoId: videoId,
        playerVars: {
            controls: 1,
            disablekb: 1,
            rel: 0
        },
        events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange,
            'onPlaybackRateChange' : onPlayerPlaybackRateChange
        }
    });
}

// 비디오를 로드하고 재생
function onPlayerReady(event) {
    // 최종재생시간을 서버에서 가져옴
    loadFnlPositionFromServer().then((serverFnlPosition) => {
        fnlPosition = serverFnlPosition;
        console.log("마지막으로 재생된 시간 : " + fnlPosition);
        // 비디오가 시작될 때 최종재생시간부터 이어서 재생
        if (fnlPosition > 0) {
            // 비디오는 로드하지만 재생시키지 않음
            event.target.cueVideoById(videoId, fnlPosition);
        } else {
            // 최종재생시간이 이면 비디오 로드
            event.target.loadVideoById(videoId, fnlPosition);
            event.target.stopVideo();
        }
    });
}

function onPlayerStateChange(event) {
    if (event.data === YT.PlayerState.PLAYING) { //비디오가 재생중일때
        fnlPosition = player.getCurrentTime(); // 현재 재생 위치 가져오기
        maxPosition = loadMaxPositionFromServer(); // 서버에서 최대 재생 위치 가져오기
        clearInterval(intervalId); //setInterval() 실행하기 전 중단
        intervalId = setInterval(() => {
            fnlPosition = player.getCurrentTime();
            updateVideoPosition(); //최종, 최대재생시간 업데이트
            console.log("최종재생시간 : " + fnlPosition);
            console.log("최대재생시간 : " + maxPosition);
            sendProgressToServer(); //업데이트 후 진행률 서버로 전송
        }, 5000); // 5초마다 실행

        // 현재재생위치가 최대재생위치를 초과하면 현재재생위치로 되돌림
        loadMaxPositionFromServer().then((serverMaxPosition) => {
            maxPosition = serverMaxPosition; //서버에서 최대재생시간 가져옴
            // 만약, 현재재생시간이 최대재생시간을 넘으면 최대재생시간로 되돌림
            if (event.target.getCurrentTime() > maxPosition + 1) {
                event.target.seekTo(maxPosition);
            }
        });
        sendProgressToServer(); //일시정지 후 다시 재생할 때 진행률 전송
    } else if (event.data === YT.PlayerState.PAUSED) { // 비디오가 일시정지일때
        // 재생 중이 아니면 interval을 해제하여 업데이트를 중지
        clearInterval(intervalId);
        // 일시정지한 시간을 기록하고 다시 재생했을때 일시정지된 시간부터 재생
        fnlPosition = player.getCurrentTime();
        // 일시정지위치에서 정확한 재생을 위한 약간의 5초 여유 시간 ??
        if (event.target.getCurrentTime() <= maxPosition + 5) {
            updateVideoPosition();
            console.log("일시정지 시 최종재생시간 : " + fnlPosition);
        }
        // 비디오가 끝나더라도 1초 이전으로 되돌려서 일시정지(다음 비디오를 안전하게 실행하기 위함)
    } else if (event.data === YT.PlayerState.ENDED) { //비디오가 끝났을 때
        console.log("영상이 끝나기 -1초 전의 시간 : " + maxPosition);
        event.target.seekTo(event.target.getDuration() - 1);
        event.target.pauseVideo();
        sendProgressToServer();
    }
}

//재생속도가 변경될 때 1을 초과하면 1로 변경
function onPlayerPlaybackRateChange(event){
    if (event.target.getPlaybackRate() > 1){
        event.target.setPlaybackRate(1);
    }
}

// 최종 재생 위치와 최대 재생 위치를 같이 업데이트(5초마다 저장되어야 함.)
function updateVideoPosition() {
    // 현재 비디오 위치 가져오기
    const currentTime = player.getCurrentTime();
    maxPosition = currentTime;
    // 최종 재생 위치 업데이트
    finalPosition = currentTime;

    fetch(`/youtube/api/savePlayTime?magId=${magId}&fnlPosi=${finalPosition}&maxPosi=${maxPosition}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ maxPosition, finalPosition })
    })
        .then(response => {
            if (response.ok) {
                console.log('비디오 위치 업데이트 성공');
            } else {
                console.error('비디오 위치 업데이트 실패');
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
}

// 서버에서 최대 재생 위치를 불러옴
function loadMaxPositionFromServer() {
    return fetch(`/youtube/api/getMaxPosi`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                console.error('최대 재생 위치 불러오기에 실패했습니다.');
                return 0; // 기본값 반환
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
            return 0; // 기본값 반환
        });
}

// 서버에서 최종 재생 위치를 불러옴
function loadFnlPositionFromServer() {
    return fetch(`/youtube/api/getFnlPosi`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                console.error('최대 재생 위치 불러오기에 실패했습니다.');
                return 0; // 기본값 반환
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
            return 0; // 기본값 반환
        });
}

// 진행률 서버에 저장
async function sendProgressToServer() {
    try {
        const runTime = player.getDuration(); // 전체 재생 시간
        const maxPosition = await loadMaxPositionFromServer(); // 사용자가 최대로 본 시간
        const progress = ((maxPosition / runTime) * 100).toFixed(2);
        console.log("진행률 : " + progress + '%');

        // 진행률 정보를 서버로 전송
        const response = await fetch(`/youtube/api/saveProgress?magId=${magId}&progress=${progress}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ progress }),
        });

        if (response.ok) {
            console.log('진행률 정보 서버에 전송 성공');
        } else {
            console.error('진행률 정보 서버에 전송 실패');
        }
    } catch (error) {
        console.error('오류 발생:', error);
    }
}


// 스페이스바를 눌렀을 때 비디오 재생 또는 일시정지
document.addEventListener('keydown', function(event) {
    if (event.code === 'Space' && event.target === document.body) {
        event.preventDefault(); // 페이지 스크롤 방지
        togglePlayPause(); // 비디오 재생/일시정지 토글 함수 호출
    } else if (event.code === 'ArrowLeft' && event.target === document.body) {
        event.preventDefault(); // 페이지 스크롤 방지
        rewindVideo(); // 비디오 되감기 함수 호출
    } else if (event.code === 'ArrowRight' && event.target === document.body) {
        event.preventDefault(); // 페이지 스크롤 방지
        fastForwardVideo(); // 비디오 빨리감기 함수 호출
    }
});

// 비디오 재생/일시정지 토글 함수
function togglePlayPause() {
    if (player.getPlayerState() === 1) { // 재생 중
        player.pauseVideo(); // 일시정지
    } else if (player.getPlayerState() === 2) { // 일시정지 중
        player.playVideo(); // 재생
    }
}

// 비디오 되감기
function rewindVideo() {
    const currentTime = player.getCurrentTime();
    const newTime = currentTime - 5; // 10초 뒤로 이동 (조절 가능)
    if (newTime < 0) {
        player.seekTo(0);
    } else {
        player.seekTo(newTime);
    }
}

// 비디오 빨리감기
function fastForwardVideo() {
    const currentTime = player.getCurrentTime();
    const duration = player.getDuration();
    const newTime = currentTime + 5; // 10초 앞으로 이동 (조절 가능)
    if (newTime > duration) {
        player.seekTo(duration);
    } else {
        player.seekTo(newTime);
    }
}