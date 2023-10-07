//  summernote 설정
// $('#summernote').summernote('code', '<p>가나다</p><p>마바사</p><p>아자차카타파하</p>');
$('#summernote').summernote({
    toolbar: [
        ['fontname', ['fontname']],
        ['fontsize', ['fontsize']],
        ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
        ['color', ['forecolor','color']],
        ['table', ['table']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['height', ['height']],
        ['insert',['picture','link']],
        ],
    height: 500,                 // 에디터 높이
    minHeight: 500,             // 최소 높이
    maxHeight: 500,             // 최대 높이
    maxWidth: 1200,
    focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
    lang: "ko-KR",					// 한글 설정
    placeholder: '내용을 입력해주세요.'	//placeholder 설정
});

//마지막으로 한 행동 취소 ( 뒤로가기 )
$('#summernote').summernote('undo');
// 앞으로가기
$('#summernote').summernote('redo');
// 기존 작성값 불러오기(html로 변환해서 가져와야 양식 유지)
// $('#summernote').summernote('pasteHTML', data);

const saveBtn = document.getElementById('saveBtn');

saveBtn.addEventListener('click' , ()=>{

    // 게시판 종류 값 가져오기
    const boardType = document.getElementById('boardType').value;
    // 사용자 id 값 가져오기
    const memberId = document.getElementById('memberId').value;
    // 사용자 이름 값 가져오기
    const memberName = document.getElementById('memberName').value;
    // 게시글 제목 가져오기
    const title = document.getElementById('title').value;
    // 서머 노트 입력값 가져오기
    const content = $('#summernote').summernote('code');
    // 비공개 글 여부 체크 값 가져오기
    let privateCk = false;
    if(document.getElementById('private_ck').checked == true){
        privateCk = true;
    }

    console.log("privateCk : " + privateCk);
    console.log("boardType : " + boardType);
    console.log("memberId : " + memberId);
    console.log("memberName : " + memberName);
    console.log("title : " + title);
    console.log("content : " + content);

    let data = {
        type : boardType,
        memberId : memberId,
        writer : memberName,
        title : title,
        content : content ,
        isLocked : privateCk
    };


    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/board/write' , true);
    xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');

    xhr.onreadystatechange = function (){
        if(xhr.readyState === 4){
            // 성공 응답시
            if(xhr.status === 201){
                alert('등록됨');
            }
            else {
                alert('실패');
            }
        }
    };
    xhr.send(JSON.stringify(data));


});

