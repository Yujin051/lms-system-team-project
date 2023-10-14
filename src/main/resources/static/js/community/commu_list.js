/**
 * @author 임승범
 */

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

// 검색버튼
const searchBtn = document.getElementById('searchBtn');

// 검색버튼 클릭 이벤트
if(searchBtn){
    searchBtn.addEventListener('click', () => {
        let searchType = document.getElementById('search_select').value;
        let searchValue = document.getElementById('search_input').value;
        let boardType = document.getElementById('boardType').value;

        if(searchValue === null || searchValue === ""){
            alert('검색하실 내용을 입력해주세요.');
            return;
        }

        let postUrl = "/board/search/";

        if (searchType === 'title') {
            postUrl += "title";
        } else if (searchType === 'content') {
            postUrl += "content";
        } else {
            postUrl += "writer";
        }

        postUrl += `/${boardType}?searchValue=${searchValue}`;

        let xhr = new XMLHttpRequest();
        xhr.open('GET', postUrl, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    window.location = postUrl;
                } else {
                    alert('검색실패 : firetrap5319@gmail.com 문의 바람.');
                }
            }
        };
        xhr.send();
    });
}