
// 모달창 토글 display
const modalOpen = document.getElementById('modalOpenBtn');
const modalClose = document.getElementById('modalCloseBtn');
const modal = document.getElementById('modalContainer');

// 추가 버튼 클릭시
modalOpen.addEventListener('click', () => {
    modal.style.display = 'flex';
    
});
// 닫기(x) 버튼 클릭시
modalClose.addEventListener('click', () => {
    modal.style.display = 'none';
});


// 모달창의 참가자 체크박스 체크시 모든 이름 li태그 체크
function selectAll(selectAll){

    const classmates = document.getElementsByName('classmate');

    classmates.forEach( item => {
        item.checked = selectAll.checked;
    });
    
};


// li 요소를 클릭할 때 이벤트 리스너 등록
const classItems = document.querySelectorAll('.classtype li');
classItems.forEach(classItem => {
    classItem.addEventListener('click', () => {
        const className = classItem.textContent; // 수업 이름 가져오기

        const classmateUl = document.querySelector('.classmate');
        
        if(classmateUl.children.length != 0){
            while (classmateUl.firstChild) {
                classmateUl.removeChild(classmateUl.firstChild);
            }   
        }
        
        // 서버로 요청을 보내고 참가자 목록을 가져오는 함수 호출
        // fetchDataFromClass(className);
    });
});

// function fetchDataFromClass(className) {
//     // XMLHttpRequest 객체 생성
//     const xhr = new XMLHttpRequest();
    
//     // post 요청 설정
//     xhr.open('post', '#', true);

//     // 응답을 받았을 때 처리할 콜백 함수 등록
//     xhr.onload = function() {
//         if (xhr.status >= 200 && xhr.status < 300) {
//             // 성공적으로 데이터를 받았을 때 처리할 코드
            
//             // 수업 참가자 이름이 배열로 들어있을 것.
//             const response = xhr.responseText;
//             const classmateUl = document.getElementsByClassName('classmate');

            
//             response.forEach(name => {
//                 // 이름 갯수만큼 li만들어서 안에 넣어주기
//                 const li = document.createElement('li');
//                 li.innerHTML = '<input type="checkbox" name="classmate">' + name;
//                 classmateUl.appendChild(li);
//             });


//         } else {
//             // 요청이 실패했을 때 처리할 코드
//             console.error('Request failed with status:', xhr.status);
//         }
//     };

//     // 요청 보내기
//     xhr.send();
// }




// 참가자 추가 버튼 클릭시 이벤트
// document.getElementById('selectBtn').addEventListener('click', () => {
//     const classmateCheckboxes = document.querySelectorAll('.classmate input[type="checkbox"]:checked');
//     const msgToUl = document.querySelector('.msgTo');

//     classmateCheckboxes.forEach(checkbox => {
//         const classmateName = checkbox.parentNode.textContent.trim(); // li 요소의 텍스트 값 가져오기

//         const li = document.createElement('li');
//         li.innerHTML = `${classmateName}<span>X</span>`;

//         // 받는사람 li 갯수 제한
//         if(msgToUl.children.length < 10){
//             msgToUl.appendChild(li);
//         }
//         else {
//             alert('최대 10명까지에게만 보낼 수 있습니다.');
//         }
        
//         // 받는 사람 목록의 X 버튼에 클릭 이벤트 추가 (삭제 기능)
//         const removeBtn = li.querySelector('span');
//         removeBtn.addEventListener('click', () => {
//             // li 요소 삭제
//             msgToUl.removeChild(li);
//             // 업데이트된 받는 사람의 숫자 표시
//             const toNum = document.getElementById('toNum');
//             toNum.textContent = msgToUl.children.length;
//         });
//     });

//     // 업데이트된 받는 사람의 숫자 표시
//     const toNum = document.getElementById('toNum');
//     toNum.textContent = msgToUl.children.length;

// });

document.getElementById('selectBtn').addEventListener('click', () => {
    const classmateCheckboxes = document.querySelectorAll('.classmate input[type="checkbox"]:checked');
    const msgToUl = document.querySelector('.msgTo');

    const totalRecipients = msgToUl.children.length;
    
    // 받는 사람 목록이 10명 이하일 때만 추가
    if (totalRecipients <= 10) {
        classmateCheckboxes.forEach(checkbox => {
            const classmateName = checkbox.parentNode.textContent.trim(); // li 요소의 텍스트 값 가져오기

            const li = document.createElement('li');
            li.innerHTML = `${classmateName}<span>X</span>`;

            msgToUl.appendChild(li);

            // 받는 사람 목록의 X 버튼에 클릭 이벤트 추가 (삭제 기능)
            const removeBtn = li.querySelector('span');
            removeBtn.addEventListener('click', () => {
                // li 요소 삭제
                msgToUl.removeChild(li);
                // 업데이트된 받는 사람의 숫자 표시
                const toNum = document.getElementById('toNum');
                toNum.textContent = msgToUl.children.length;
            });
        });

        // 업데이트된 받는 사람의 숫자 표시
        const toNum = document.getElementById('toNum');
        toNum.textContent = msgToUl.children.length;
    } else {
        // 받는 사람이 10명을 초과하는 경우 경고 메시지 출력
        alert('최대 10명까지에게만 보낼 수 있습니다.');
    }
});