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

if(searchBtn){
    searchBtn.addEventListener('click' , ()=>{

        let searchType = document.getElementById('search_select').value;
        let searchValue = document.getElementById('search_input').value;

        let searchTitle = '/board/search/title';
        let searchContent = '/board/search/content';
        let searchWriter = '/board/search/writer';

        let postUrl = "";

        if(searchValue == null || searchValue == ""){
            alert('검색하실 내용을 입력해주세요.');
            return ;
        }
        else{

            if(searchType === 'title'){
                postUrl = searchTitle;
            }
            else if(searchType === 'content'){
                postUrl = searchContent;
            }
            else{
                postUrl = searchWriter;
            }

            let searchData = {
                searchType : searchType,
                searchValue : searchValue
            };

            let xhr = new XMLHttpRequest();
            xhr.open('POST' , postUrl , true);
            xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
            xhr.onreadystatechange = function (){
                if (xhr.readyState === 4){
                    if(xhr.status === 200){
                        alert('성공');
                    }
                    else{
                        alert('실패');
                    }
                }
            };
            xhr.send(JSON.stringify(searchData));
        }
    });
}