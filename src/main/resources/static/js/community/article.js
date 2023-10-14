/**
 * @author 임승범
 */

let modifyBtn = document.getElementById('modifyBtn');
let deleteBtn = document.getElementById('deleteBtn');
let backBtn = document.getElementById('backBtn');

let type = document.getElementById('boardType').value;
let articleId = document.getElementById('articleId').value;

// 수정 버튼 이벤트, 글 작성 페이지로 이동
if(modifyBtn){
    modifyBtn.addEventListener('click' , ()=>{
        console.log('click');
        window.location = "/board/write/" + type + "?id=" + articleId;
    });
}

// 돌아가기 버튼 이벤트 , 전페이지로.
if (backBtn){
    backBtn.addEventListener('click' , ()=>{
        window.history.back();
    });
}

// 삭제하기 버튼 이벤트, 게시글 삭제 post
if(deleteBtn){
    deleteBtn.addEventListener('click' , ()=>{


        let data = {
            articleId : articleId
        };
        console.log(data);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/board/deleted" , true);
        xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');

        xhr.onreadystatechange = function (){
            if(xhr.readyState === 4) {
                if (xhr.status === 200) {
                    alert('삭제됨');
                    window.location = "/board/list/" + type;
                }
                else {
                    alert('실패');
                }
            }
        };
        xhr.send(JSON.stringify(data));


    });
}


let isDel = document.getElementById('isDel').value; // 삭제처리
let isLock = document.getElementById('isLock').value; // 비공개처리
let memId = document.getElementById('memId').value; // 사용자 번호
let artMemId = document.getElementById('artMemId').value; // 작성자 번호

window.onload = function (){
    if(isDel === "true"){
        window.history.back();
    }

    if(isLock === "true"){
        if(artMemId !== memId){
            window.history.back();
        }
    }
}

// 댓글 작성
let commentBtn = document.getElementById('commentBtn');
if(commentBtn){

    commentBtn.addEventListener('click' , ()=>{

        let commentText = document.getElementById('write_comment').value;
        console.log(commentText);
        let str = commentText.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        console.log(str);

        let data = {
            commentText : str,
            boardArticleId : articleId
        };

        let xhr = new XMLHttpRequest();
        xhr.open('POST' , '/comment/write' , true);
        xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
        xhr.onreadystatechange = function (){
            if(xhr.readyState === 4){
                if(xhr.status === 201){
                    alert('성공');
                    location.reload();
                }
                else {
                    alert('실패 : firetrap5319@gmail.com로 문의 바랍니다.');
                }
            }
        };
        xhr.send(JSON.stringify(data));
    });

}
// var str = document.getElementById("textarea").value;
// str = str.replaceAll("<br/>", "\r\n");
// document.getElementById("textarea").value = str;

// 댓글 메뉴 드롭다운 수정 버튼
function commentModify(id){
    // 메뉴버튼
    let dropdownMenu = document.getElementById('dropdownMenu' + id);
    dropdownMenu.style.display = 'none'; // 메뉴버튼 사라지도록.
    // 글 작성 textarea
    let textareaId = document.getElementById('textarea' + id);
    textareaId.removeAttribute('readonly'); // 읽기전용 해제
    // 글 등록/취소 기능 버튼 박스
    let commentBtnBox = document.getElementById('comment_btnBox' + id);
    commentBtnBox.style.display = 'block';
}

// 댓글 내용 수정 등록 버튼
function commentUpdate(id){
    //
    // 글 작성 textarea
    let textareaId = document.getElementById('textarea' + id).value;
    console.log(textareaId);
    let str = textareaId.replace(/(?:\r\n|\r|\n)/g, '<br/>');
    console.log(str);

    let data = {
        commentText : str,
        boardArticleId : articleId
    };

    let xhr = new XMLHttpRequest();
    xhr.open('POST' , '/comment/modify' , true);
    xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
    xhr.onreadystatechange = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 201){
                alert('성공');
                location.reload();
            }
            else {
                alert('실패 : firetrap5319@gmail.com로 문의 바랍니다.');
            }
        }
    };
    xhr.send(JSON.stringify(data));
}




// 댓글 내용 수정 취소
function commentCancel(){
    window.location.reload();
}
































