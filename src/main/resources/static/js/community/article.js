
let modifyBtn = document.getElementById('modifyBtn');
let deleteBtn = document.getElementById('deleteBtn');
let backBtn = document.getElementById('backBtn');

let type = document.getElementById('boardType').value;
let articleId = document.getElementById('articleId').value;

modifyBtn.addEventListener('click' , ()=>{
    console.log('click');
    window.location = "/board/write/" + type + "?id=" + articleId;
});

// 돌아가기 버튼 이벤트 , 전페이지로.
backBtn.addEventListener('click' , ()=>{
   window.history.back();
});