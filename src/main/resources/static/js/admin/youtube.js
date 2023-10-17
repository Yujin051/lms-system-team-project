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

// 비디오를 로드하고 재생
function onPlayerReady(event) {
    event.target.loadVideoById(videoId, fnlPosition);
    event.target.playVideo();
    player.getDurationAsync().then(duration => {
        const runTime = duration - 5;
        console.log("runTime : " + runTime);
        const progress = (maxPosition / runTime) * 100; // 진행률
        console.log("진행률 : " + progress);
    });
}

let maxPosition = 0; // 최대 재생 위치 초기화
let fnlPosition = 0; // 최종 재생 위치 초기화
function onPlayerStateChange(event) {
    if (event.data === YT.PlayerState.PLAYING) {
        let runTime = player.getDuration() - 5;
        fnlPosition = player.getCurrentTime(); // 현재 재생 위치 가져오기
        maxPosition = loadMaxPositionFromServer(); // 서버에서 최대 재생 위치 가져오기
        clearInterval(intervalId);
        console.log("Loaded maxPosition from server: " + maxPosition);
        console.log("bottomseekToFnl4 : " + fnlPosition);
        intervalId = setInterval(() => {
            fnlPosition = player.getCurrentTime();
            console.log("5secondFnlPosi : " + fnlPosition);
            console.log("5secondMaxPosi : " + maxPosition);
            updateVideoPosition();
        }, 3000); // 5초마다 실행


        loadMaxPositionFromServer().then((serverMaxPosition) => {
            maxPosition = serverMaxPosition;
            if(event.target.getCurrentTime() > Number(maxPosition) + 1){
                event.target.seekTo(maxPosition);
            }
            if(event.target.getCurrentTime() >= runTime ){
                player.pauseVideo();
                player.seekTo(fnlPosition);
            }
            console.log("playingRunTime : " + runTime);
        });

    } else if(event.data === YT.PlayerState.PAUSED) {
        // 재생 중이 아니면 interval을 해제하여 업데이트를 중지
        clearInterval(intervalId);
        //일시정지한 시간을 기록하고 다시 재생했을때 일시정지된 시간부터 재생
        if(event.target.getCurrentTime() <= maxPosition + 5){
            updateVideoPosition();
        }
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
    // clearInterval(intervalId);
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

// 스페이스바를 눌렀을 때 비디오 재생 또는 일시정지
document.addEventListener('keydown', function(event) {
    if (event.code === 'Space' && event.target === document.body) {
        event.preventDefault(); // 페이지 스크롤 방지
        togglePlayPause(); // 비디오 재생/일시정지 토글 함수 호출
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
