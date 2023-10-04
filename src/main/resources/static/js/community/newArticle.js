//  summernote 설정
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
    focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
    lang: "ko-KR",					// 한글 설정
    placeholder: '내용을 입력해주세요.'	//placeholder 설정
});

//마지막으로 한 행동 취소 ( 뒤로가기 )
$('#summernote').summernote('undo');
// 앞으로가기
$('#summernote').summernote('redo');