// 검색 버튼
const searchBtn = document.getElementById('searchBtn');

if(searchBtn){
    searchBtn.addEventListener('click' , () =>{

        let searchType = document.getElementById('search_select').value;
        let searchValue = document.getElementById('search_input').value;
        // let listType = document.getElementById('listType').value;

        if(searchValue === null || searchValue === ""){
            alert('검색하실 내용을 입력해주세요');
            return;
        }

        let postUrl = "/board/msg/search/";

        if (searchType === 'title') {
            postUrl += "title";
        } else if (searchType === 'content') {
            postUrl += "content";
        } else {
            postUrl += "recv";
        }

        postUrl += `/?searchValue=${searchValue}`;

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