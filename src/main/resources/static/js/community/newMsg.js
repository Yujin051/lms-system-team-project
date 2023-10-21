/**
 * @author 임승범
 */
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

// 모달창 선택완료 버튼
const modalSelectedBtn = document.getElementById('modelSelBtn');
modalSelectedBtn.addEventListener('click' , () => {

    const msgToUl = document.querySelector('.msgTo');
    const toUl = document.getElementById('toUl');
    // 넣을 곳에 자식 있는지 확인, 있다면 모두 삭제
    if(toUl.children.length > 0){
        while (toUl.children.length > 0) {
            toUl.removeChild(toUl.children[0]);
        }
    }

    let msgToLiElements = msgToUl.children;
    // 자식이 있다면 복사해서 넣을 곳 ul에 넣어주기
    if(msgToLiElements.length > 0){
        Array.from(msgToLiElements).forEach(li => {

            let liClone = li.cloneNode(false);  // li 갯수만큼 li 생산
            let liText = li.textContent.replace(/X/g,'');
            liClone.textContent = liText;
            console.log(liClone);
            toUl.appendChild(liClone);
        });
        modal.style.display = 'none';   // 모달 닫기
    }
    else {  // 없을시 경고.
        alert('받을 사람이 없습니다.');
    }

});



// 모달창의 참가자 체크박스 체크시 모든 이름 li태그 체크
function selectAll(selectAll){

    const classmates = document.getElementsByName('classmate');

    classmates.forEach( item => {
        item.checked = selectAll.checked;
    });
    
}

// 수업 클래스 li 요소를 클릭할 때 이벤트 리스너 등록
function getClassMate(id){

    console.log("id = " + id);

    let data = {
        classId : id
    };

    // 참가자ul 태그 가져오기
    const classmateUl = document.querySelector('.classmate');
    // 참가자 자리 비우기(li가 없어질때까지 삭제해라)
    if(classmateUl.children.length !== 0){
        while (classmateUl.firstChild) {
            classmateUl.removeChild(classmateUl.firstChild);
        }
    }

    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/board/msg/getClassMate', true);
    xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
    xhr.onreadystatechange = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                // alert('성공');
                let classmates = JSON.parse(xhr.response);
                console.log("response = " + xhr.response);
                console.log("classmates = " + classmates);
                addClassmate(classmates);
            }
            else {
                alert('실패 : firetrap5319@gmail.com 으로 문의 바랍니다.');
            }
        }
    };
    xhr.send(JSON.stringify(data));
}

function addClassmate(classmates){
    // 참가자ul 태그 가져오기
    const classmateUl = document.querySelector('.classmate');

    classmates.forEach(classmate =>{
        // 이름 갯수만큼 li만들어서 안에 넣어주기
        const li = document.createElement('li');
        li.innerHTML = '<input type="checkbox" name="classmate">' + classmate.memberName + '(' + classmate.loginId + ')';
        classmateUl.appendChild(li);
    });
}



//참가자 추가 버튼 클릭시 이벤트
document.getElementById('selectBtn').addEventListener('click', () => {
    const classmateCheckboxes = document.querySelectorAll('.classmate input[type="checkbox"]:checked');
    const msgToUl = document.querySelector('.msgTo');

    if (msgToUl.children.length + classmateCheckboxes.length > 10) {
        alert('최대 10명까지에게만 보낼 수 있습니다.');
        return; // 경고 주고 바로 종료
    }

    classmateCheckboxes.forEach(checkbox => {
        const classmateName = checkbox.parentNode.textContent.trim(); // li 요소의 텍스트 값 가져오기

        const li = document.createElement('li');
        li.innerHTML = `${classmateName}<span>X</span>`;

        msgToUl.appendChild(li);
        // 받는 사람 목록의 X 버튼에 클릭 이벤트 추가 (삭제 기능)
        const removeBtn = li.querySelector('span');
        removeBtn.addEventListener('click', () => {
            msgToUl.removeChild(li);    // li 요소 삭제

            // 동일한 내용의 li checkbox의 체크 해제
            let liText = li.textContent.replace(/X/g,'');   // li안에 X 뺀 본래 값
            classmateCheckboxes.forEach(li =>{  //모든 체크박스에 반복 돌리고
                if(liText === li.parentNode.textContent.trim()){    // 본래값 동일?
                    li.checked = false; // 체크해제
                }
            })

            // 업데이트된 받는 사람의 숫자 표시
            const toNum = document.getElementById('toNum');
            toNum.textContent = msgToUl.children.length;
        });
    });

    // 업데이트된 받는 사람의 숫자 표시
    const toNum = document.getElementById('toNum');
    toNum.textContent = msgToUl.children.length;

});

// 취소버튼 클릭시
const cancelBtn = document.getElementById('cancelBtn');
cancelBtn.addEventListener('click' , () => {
    window.history.back();
});

if(document.getElementById('writeMsgBtn')){

//전송 버튼 클릭시
    const writeMsgBtn = document.getElementById('writeMsgBtn');

    writeMsgBtn.addEventListener('click' , ()=>{

        const toUl = document.getElementById('toUl');   // 받는사람 목록
        let msgTitle = document.getElementById('msgTitle').value;   // 제목 값
        let msgCont = document.getElementById('msgText').value;     // 내용 값

        if(toUl.children.length <= 0){
            alert('받을 사람을 입력해주세요.');
            return; // 종료시킴.
        }
        else if(msgTitle == null || msgTitle === ""){
            alert('제목을 입력해주세요');
            return;
        }
        else if(msgCont == null || msgCont === ""){
            alert("내용을 입력해주세요");
            return;
        }

        // 받는 사람 목록의 각 li 요소에서 로그인 아이디 추출하여 배열에 저장
        const loginIds = Array.from(toUl.children).map(li => {
            const text = li.textContent.trim(); // li 요소의 텍스트 추출
            const match = text.match(/\(([^)]+)\)/); // 괄호로 묶인 부분을 추출하는 정규표현식

            // 매칭되는 부분이 있다면 괄호 안의 내용(로그인 아이디) 반환
            if (match && match.length === 2) {
                return match[1]; // 로그인 아이디
            } else {
                // 매칭되는 부분이 없다면 빈 문자열 또는 에러 처리
                return '';
            }
        }).filter(id => id !== ''); // 빈 문자열을 필터링하여 정상적인 로그인 아이디만 남김

        // 서버에 전송할 데이터 형식
        let data = {
            toSome: loginIds,
            msgTitle: msgTitle,
            msgCont: msgCont
        };

        let xhr = new XMLHttpRequest();
        xhr.open('POST' , '/board/msg/write' , true);
        xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');

        xhr.onreadystatechange = function () {
            if(xhr.readyState === 4){
                if(xhr.status === 201){
                    // alert('성공');
                    window.location = '/board/msg/all';
                }
                else {
                    alert('실패 : firetrap5319@gmail.com 문의 바랍니다.');
                }
            }
        };
        xhr.send(JSON.stringify(data));
    });
}



//답장 버튼 클릭시
if(document.getElementById('reMsgBtn')){
    const reMsgBtn = document.getElementById('reMsgBtn');

    reMsgBtn.addEventListener('click' , ()=>{

        const toUl = document.getElementById('toUl');   // 받는사람 목록
        let msgTitle = document.getElementById('msgTitle').value;   // 제목 값
        let msgCont = document.getElementById('msgText').value;     // 내용 값
        let orgMsgId = document.getElementById('msgOrgId').value; // 이전 메시지 id

        if(toUl.children.length <= 0){
            alert('받을 사람을 입력해주세요.');
            return; // 종료시킴.
        }
        else if(msgTitle == null || msgTitle === ""){
            alert('제목을 입력해주세요');
            return;
        }
        else if(msgCont == null || msgCont === ""){
            alert("내용을 입력해주세요");
            return;
        }

        // 받는 사람 목록의 각 li 요소에서 로그인 아이디 추출하여 배열에 저장
        const loginIds = Array.from(toUl.children).map(li => {
            const text = li.textContent.trim(); // li 요소의 텍스트 추출
            const match = text.match(/\(([^)]+)\)/); // 괄호로 묶인 부분을 추출하는 정규표현식

            // 매칭되는 부분이 있다면 괄호 안의 내용(로그인 아이디) 반환
            if (match && match.length === 2) {
                return match[1]; // 로그인 아이디
            } else {
                // 매칭되는 부분이 없다면 빈 문자열 또는 에러 처리
                return '';
            }
        }).filter(id => id !== ''); // 빈 문자열을 필터링하여 정상적인 로그인 아이디만 남김

        // 서버에 전송할 데이터 형식
        let data = {
            toSome: loginIds,
            msgTitle: msgTitle,
            msgCont: msgCont,
            orgMsgId : orgMsgId
        };

        let xhr = new XMLHttpRequest();
        xhr.open('POST' , '/board/msg/write' , true);
        xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');

        xhr.onreadystatechange = function () {
            if(xhr.readyState === 4){
                if(xhr.status === 201){
                    // alert('성공');
                    window.location = '/board/msg/all';
                }
                else {
                    alert('실패 : firetrap5319@gmail.com 문의 바랍니다.');
                }
            }
        };
        xhr.send(JSON.stringify(data));
    });
}
