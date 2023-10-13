// YouTube IFrame API 로드
let tag = document.createElement('script');
tag.src = 'https://www.youtube.com/iframe_api';
let firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// YouTube 동영상의 재생 및 정지 이벤트를 처리하는 함수
let player;
let lastPlayTime = 0;

function onYouTubePlayerAPIReady() {
    player = new YT.Player('player', {
        videoId: 'WJQlgBu7Qig', // 재생할 YouTube 동영상 ID
        events: {
            onReady: function (event) {
                // 자동 재생을 뺐으므로 이 부분을 주석 처리합니다.
                // event.target.playVideo();

                // 동영상이 준비되면 이벤트 핸들러를 설정
                event.target.addEventListener('onStateChange', function (event) {
                    if (event.data == YT.PlayerState.PLAYING) {
                        // 동영상이 재생 중일 때
                        let currentTime = player.getCurrentTime(); // 현재 재생 시간(초)
                        currentTime = parseInt(currentTime); // 정수로 변환
                        console.log('재생 중 - 현재 재생 시간: ' + currentTime + '초');
                    } else if (event.data == YT.PlayerState.PAUSED || event.data == YT.PlayerState.ENDED) {
                        // 동영상이 일시 정지 또는 종료될 때
                        lastPlayTime = player.getCurrentTime(); // 마지막 재생 시간 저장
                        lastPlayTime = parseInt(lastPlayTime); // 정수로 변환
                        console.log('일시 정지 또는 종료 - 마지막 재생 시간: ' + lastPlayTime + '초');

                        // 마지막 재생 시간을 서버 또는 데이터베이스에 저장
                        console.log("lastPlayTime : " + lastPlayTime);
                        saveLastPlayTimeToDatabase(lastPlayTime);
                    }
                });
            }
        }
    });
}

const magId = document.querySelector('.magId').value
console.log("magId : " + magId)
function saveLastPlayTimeToDatabase(playTime) {

    fetch(`/admin/api/fnlPosi/save?magId=${magId}&lastPlayTime=${lastPlayTime}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body : {
            magId: magId, // 적절한 값을 넣어주세요
            lastPlayTime: playTime,
        },
    })
        .then(response => response.json())
        .then(data => {
            console.log('마지막 재생 시간 저장 성공:', data);
        })
        .catch(error => {
            console.error('마지막 재생 시간 저장 오류:', error);
        });
}

