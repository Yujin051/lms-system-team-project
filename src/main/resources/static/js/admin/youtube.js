const videoId = 'FXoPu6PqPH4';

const tag = document.createElement('script');
tag.src = 'https://www.youtube.com/iframe_api';
const firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

const magId = document.querySelector('.magId').value;
// YouTube API 로드 후 실행될 함수 정의
let player;
let intervalId;
let lastFinalPosition = 0; // 마지막으로 저장한 최종 재생 위치를 추적
function onYouTubeIframeAPIReady() {
    // 플레이어 생성 및 옵션 설정
    player = new YT.Player('player', {
        height: '360',
        width: '640',
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

// 플레이어가 준비되었을 때 호출될 함수
function onPlayerReady(event) {
    // 비디오를 로드하고 재생
    event.target.loadVideoById(videoId);
    event.target.playVideo();
    if (event.target.getCurrentTime() > Number(maxPosition) + 1) {
        player.seekTo(lastFinalPosition, true);
        console.log("lastFinalPosition : " + lastFinalPosition)
    }
}

let maxPosition = 0; // 초기값 설정
let fnlPosition = 0;
function onPlayerStateChange(event) {
    if (event.data === YT.PlayerState.PLAYING) {
        intervalId = setInterval(() => {
            fnlPosition = player.getCurrentTime();
            console.log("fnlPosi : " + fnlPosition);
            console.log("maxPosi : " + maxPosition);
            updateVideoPosition();
        }, 3000); // 5초마다 실행

        const currentTime = player.getCurrentTime(); // 현재 비디오 위치 가져오기


        fnlPosition = player.getCurrentTime();
        maxPosition = loadMaxPositionFromServer(); // 서버에서 최대 재생 위치 가져오기
        // fnlPosition = loadFnlPositionFromServer(); // 서버에서 최종 재생 위치 가져오기

        saveFinalPositionToServer(fnlPosition);
        saveMaxPositionToServer(fnlPosition);


    } else if(event.data === YT.PlayerState.PAUSED) {
        // 재생 중이 아니면 interval을 해제하여 업데이트를 중지
        clearInterval(intervalId);
        // 만약 최종 재생 위치가 최대 재생 위치보다 앞서있으면 최대 재생 위치를 조정
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
    const maxPosition = currentTime;
    // 최종 재생 위치 업데이트
    const finalPosition = currentTime;


    // 서버에 최종 재생 위치 및 최대 재생 위치를 보내서 업데이트
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

// 최종 재생 위치만 저장
function saveFinalPositionToServer(finalPosition) {
    fetch(`/youtube/api/saveFnlPosi?magId=${magId}&fnlPosi=${finalPosition}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ finalPosition }),
    })
        .then(response => {
            if (response.ok) {
                console.log('최종 재생 위치가 서버에 성공적으로 저장되었습니다.');
            } else {
                console.error('최종 재생 위치 저장에 실패했습니다.');
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

// 최대 재생 위치를 서버에 저장
function saveMaxPositionToServer(maxPosition) {
    fetch(`/youtube/api/saveMaxPosi?magId=${magId}&maxPosi=${maxPosition}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ maxPosition }),
    })
        .then(response => {
            if (response.ok) {
                console.log('최대 재생 위치가 서버에 성공적으로 저장되었습니다.');
            } else {
                console.error('최대 재생 위치 저장에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
}