
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