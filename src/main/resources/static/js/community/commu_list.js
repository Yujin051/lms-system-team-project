// 제목길이 제한 20자
document.addEventListener('DOMContentLoaded', function() {
    // 모든 title a 태그 가져오고
    var elements = document.querySelectorAll('.articleTitle a');
    var maxLength = 20; // 최대 길이
    // 반복문 , 각 a태그의 글자들이 최대크기를 넘는다면 자르고 ...  붙이기
    elements.forEach(function(element) {
        var text = element.textContent;
        if (text.length > maxLength) {
            element.textContent = text.slice(0, maxLength) + '...';
        }
    });
});