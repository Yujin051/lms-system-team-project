const videoId = 'X_zptsxjiSo';

const tag = document.createElement('script');
tag.src = 'https://www.youtube.com/iframe_api';
const firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);


const magId = document.querySelector('.magId').value;


// YouTube API 로드 후 실행될 함수 정의
let player;
let intervalId;
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
            'onStateChange': onPlayerStateChange
        }
    });
}

// 플레이어가 준비되었을 때 호출될 함수
function onPlayerReady(event) {
    // 비디오를 로드하고 재생
    event.target.loadVideoById(videoId);
    event.target.playVideo();
}


function onPlayerStateChange(event) {
    if (event.data === YT.PlayerState.PLAYING) {
        // 재생 중일 때만 위치 업데이트
        updateVideoPosition();
        const maxPosition = loadMaxPositionFromServer();
        console.log("maxPosi : " + maxPosition);
        // 5초마다 현재 재생 위치 저장
        intervalId = setInterval(updateVideoPosition, 5000);
    } else {
        // 재생 중이 아니면 interval을 해제하여 업데이트를 중지
        clearInterval(intervalId);
    }
}

function updateVideoPosition() {
    // 현재 비디오 위치 가져오기
    const currentTime = player.getCurrentTime();
    const maxPosition = currentTime;
    // 최종 재생 위치 업데이트
    const finalPosition = currentTime;
    console.log("magId : " + magId)
    console.log("fnlPosi : " + finalPosition);
    console.log("maxPosi : " + maxPosition);

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
                console.log('비디오 위치가 성공적으로 업데이트되었습니다.');
            } else {
                console.error('비디오 위치 업데이트에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
}

// 최종 재생 위치만 저장
// function saveFinalPositionToServer(finalPosition) {
//     fetch('/youtube/api/saveFnlPosi', {
//         method: 'PUT',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({ finalPosition }),
//     })
//         .then(response => {
//             if (response.ok) {
//                 console.log('최종 재생 위치가 서버에 성공적으로 저장되었습니다.');
//             } else {
//                 console.error('최종 재생 위치 저장에 실패했습니다.');
//             }
//         })
//         .catch(error => {
//             console.error('오류 발생:', error);
//         });
// }
//
// // 서버에서 최대 재생 위치를 불러옴
function loadMaxPositionFromServer() {
    // 서버에서 최대 재생 위치를 불러오는 요청
    return fetch('/youtube/api/getMaxPosi')
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
//
// // 최대 재생 위치를 서버에 저장
// function saveMaxPositionToServer(maxPosition) {
//     fetch('/your-api-endpoint-for-saving-max-position', {
//         method: 'PUT',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({ maxPosition }),
//         })
//         .then(response => {
//             if (response.ok) {
//                 console.log('최대 재생 위치가 서버에 성공적으로 저장되었습니다.');
//             } else {
//                 console.error('최대 재생 위치 저장에 실패했습니다.');
//             }
//         })
//         .catch(error => {
//                 console.error('오류 발생:', error);
//         });
// }


// let intervalId;
//
// function startAutoSave() {
//     // 5초마다 현재 재생 위치와 최대 재생 위치 저장
//     intervalId = setInterval(() => {
//         const currentTime = player.getCurrentTime(); // 현재 비디오 위치 가져오기
//         const maxPosition = loadMaxPositionFromServer(); // 서버에서 최대 재생 위치 가져오기
//         saveFinalPositionToServer(currentTime); // 최종 재생 위치 저장
//         if (currentTime > maxPosition) {
//             saveMaxPositionToServer(currentTime); // 최대 재생 위치 저장
//         }
//     }, 5000); // 5초마다 실행
// }
//
// function stopAutoSave() {
//     // 자동 저장 멈춤
//     clearInterval(intervalId);
// }
//
// // 페이지 로드 시 자동 저장 시작
// startAutoSave();


