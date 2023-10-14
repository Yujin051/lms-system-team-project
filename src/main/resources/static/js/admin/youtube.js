// YouTube IFrame API 로드
let tag = document.createElement('script');
tag.src = 'https://www.youtube.com/iframe_api';
let firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// YouTube 동영상의 재생 및 정지 이벤트를 처리하는 함수
let player;
let lastPlayTime = 0;
let maxPlayTime = 0; // 초기값은 0으로 설정
let saveInterval; // 자동 저장 간격을 관리하는 변수

function onYouTubePlayerAPIReady() {
    player = new YT.Player('player', {
        videoId: 'WJQlgBu7Qig', // 재생할 YouTube 동영상 ID
        events: {
            onReady: function (event) {
                // 동영상이 준비되면 이벤트 핸들러를 설정
                event.target.addEventListener('onStateChange', function (event) {
                    if (event.data == YT.PlayerState.PLAYING) {
                        // 동영상이 재생 중일 때
                        let currentTime = player.getCurrentTime();

                        // 사용자가 동영상을 끝까지 봤을 때만 최대 재생 위치로 설정
                        if (currentTime > maxPlayTime) {
                            maxPlayTime = currentTime; // 최대 재생 위치 업데이트
                        }
                        maxPlayTime = parseInt(maxPlayTime);

                        // 5초마다 현재 재생 위치와 최대 재생 위치를 서버로 전송
                        saveInterval = setInterval(function () {
                            savePlayTimeToServer(currentTime, maxPlayTime);
                        }, 5000);
                    } else if (event.data == YT.PlayerState.PAUSED || event.data == YT.PlayerState.ENDED) {
                        // 동영상이 일시 정지 또는 종료될 때
                        lastPlayTime = player.getCurrentTime(); // 마지막 재생 시간 저장
                        lastPlayTime = parseInt(lastPlayTime); // 정수로 변환
                        console.log('일시 정지 또는 종료 - 마지막 재생 시간: ' + lastPlayTime + '초');

                        // 일시 정지 시 자동 저장 멈춤
                        clearInterval(saveInterval);
                    }
                });
            }
        }
    });
}

// JavaScript 코드에서 'magId' 값을 가져와 요청에 포함
const magId = document.querySelector('.magId').value;
console.log("magId: " + magId);

function savePlayTimeToServer(currentTime, maxTime) {
    // 서버로 전송할 데이터 준비
    const data = {
        magId: magId,
        lastPlayTime: currentTime,
        maxPlayTime: maxTime
    };

    fetch(`/youtube/api/savePlayTime?magId=${magId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            console.log('재생 위치 저장 성공:', data);
        })
        .catch(error => {
            console.error('재생 위치 저장 오류:', error);
        });
}
