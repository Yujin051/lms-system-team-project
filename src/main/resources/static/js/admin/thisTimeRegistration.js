const grid = new tui.Grid({
    el: document.getElementById('grid'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '차시순서',
                name: 'nthSequence'
            },
            {
                header: '차시 명',
                name: 'nthName'
            },
            {
                header: '강좌명',
                name: 'lectName'
            },
            {
                header: '강의 시작 일시',
                name: 'lectStart'
            },
            {
                header: '강의 종료 일시',
                name: 'lectEnd'
            },
            {
                header: '강의 운영 상태',
                name: 'isActive'
            }
        ],
        "pagination": {
            "page": 1,
            "totalCount": 100
        }
    }],
    scrollX: false,
    scrollY: false,
    rowHeaders: ['rowNum'],
    pageOptions: {
        useClient: true,
        perPage: 5,
    },
    "columns": [
        {
            header: '차시순서',
            name: 'nthSequence'
        },
        {
            header: '차시 명',
            name: 'nthName'
        },
        {
            header: '강좌명',
            name: 'lectName'
        },
        {
            header: '강의 시작 일시',
            name: 'lectStart'
        },
        {
            header: '강의 종료 일시',
            name: 'lectEnd'
        },
        {
            header: '강의 운영 상태',
            name: 'isActive'
        },
    ],
});
console.log(grid)
//  그리드 요소에 CSS 스타일 적용
grid.el.style.width = '1500px';
grid.el.style.marginLeft = '90px';

// // 버튼 클릭시 데이터 조회
const retrieveButton = document.querySelector('.btn-secondary');
retrieveButton.addEventListener('click', async () => {
    try {
        const response = await fetch('/admin/ttr/search')
        const data2 = await response.json();
        console.log(data2);
        grid.resetData(data2);
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});


// 'name' 열의 모든 셀을 선택
const nameCells = document.querySelectorAll('.tui-grid-cell-name');

// 선택한 모든 셀에 대해 텍스트를 가운데 정렬
nameCells.forEach(cell => {
    cell.style.textAlign = 'center';
});

/*----------------하단 첫번째 테이블-----------------*/

const gridData2 = [
    {
        name: '강의 1',
        artist: '강좌명',
        type: '과목명',
        release: '2023-01-15',
        genre: '2023-10-24',
        lectureStatus:'운영중'
    },
    {
        name: '강의 2',
        artist: '강좌명',
        type: '과목명',
        release: '2023-01-15',
        genre: '2023-10-24',
        lectureStatus:'운영중'
    },
    {
        name: '강의 3',
        artist: '강좌명',
        type: '과목명',
        release: '2023-01-15',
        genre: '2023-10-24',
        lectureStatus:'운영중'
    },
    {
        name: '강의 4',
        artist: '강좌명',
        type: '과목명',
        release: '2023-01-15',
        genre: '2023-10-24',
        lectureStatus:'운영중'
    },
    {
        name: '강의 5',
        artist: '강좌명',
        type: '과목명',
        release: '2023-01-15',
        genre: '2023-10-24',
        lectureStatus:'운영중'
    },
    // 다른 데이터 항목 추가 가능
];

const grid2 = new tui.Grid({
    el: document.getElementById('grid2'),
    data: gridData2,
    "result": true,
    data: [{
        "contents": [
            {
                header: '차시순서',
                name: 'name'
            },
            {
                header: '차시명',
                name: 'artist'
            },
            {
                header: '차시 학습시간',
                name: 'type'
            }
        ],
        "pagination": {
            "page": 1,
            "totalCount": 100
        }
    }],
    scrollX: false,
    scrollY: false,
    rowHeaders: ['check'],
    pageOptions: {
        useClient: true,
        perPage: 5,
    },
    "columns": [
        {
            header: '차시순서',
            name: 'name'
        },
        {
            header: '차시명',
            name: 'artist'
        },
        {
            header: '차시 학습시간',
            name: 'type'
        },
    ],
});
//  그리드 요소에 CSS 스타일 적용
grid2.el.style.width = '700px';
grid2.el.style.marginLeft = '0px';


// 검색창 구현
/*const formTemplate = `<form action="/ttr/search" method="GET" class="form-inline" role="search">
<input type="text" name="keyword" class="form-control" id="search" placeholder="검색">
</form>`;*/

// 데이터 객체 (빈 객체)
// const data = {};

// const renderedForm = Mustache.render(template, data);

// 생성된 폼을 원하는 HTML  요소에 추가
// const formContainer = document.getElementById("")

$(document).ready(function() {
    // 첫 번째 그리드 초기화
    const container1 = document.getElementById("grid");
    const options1 = {
        // 그리드 옵션 설정 (선택적)
    };
    const grid1 = new tui.Grid(container1, options1);

    // 두 번째 그리드 초기화
    const container2 = document.getElementById("grid2");
    const options2 = {
        // 그리드 옵션 설정 (선택적)
    };
    const grid2 = new tui.Grid(container2, options2);

    // 조회 버튼 클릭 이벤트 처리
    $("#search-button").click(function() {
        // 서버로 데이터 요청
        $.ajax({
            type: "GET",
            url: "/admin/ttr/api", // Spring Boot Controller의 엔드포인트 경로
            dataType: "json",
            success: function(data) {
                // 데이터를 첫 번째 그리드에 표시
                grid1.resetData(data);
            },
            error: function() {
                // 오류 처리
                alert("데이터를 불러오는 중에 오류가 발생했습니다.");
            }
        });
    });

    // 검색 버튼 클릭 이벤트 처리
    $("#search-input").on('input', function() {
        const keyword = $(this).val();
        // 서버로 검색 데이터 요청
        $.ajax({
            type: "GET",
            url: "/admin/ttr/search", // 검색을 처리하는 엔드포인트 경로
            data: { keyword: keyword },
            dataType: "json",
            success: function(data) {
                // 검색 결과를 첫 번째 그리드에 표시
               const formControl1 = document.querySelector('nthSequence')
            },
            error: function() {
                // 오류 처리
                alert("데이터 검색 중에 오류가 발생했습니다.");
            }
        });
    });
});

/*
grid.on('click', (ev) => {
    console.log(grid.getValue(ev.rowKey, 'nthSequence'))
    const nthSequence = document.querySelector("nthSequence");
    grid2.resetData(grid2.getData());
})


// 검색조회버튼
let searchBtn = document.getElementById('search-button');

searchBtn.addEventListener('click',()=>{
    // 검색창 값
    let searchValue = document.getElementById('search-input').value;
    console.log(searchValue);

    let searchData = {
        searchValue : searchValue
    };

    // XMLHttpRequest 객체 생성
    let xhr = new XMLHttpRequest();
    // GET 요청 보내기
    xhr.open('POST', '/api/post' , true);
    xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
    // 서버 응답 처리
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // 서버 응답이 성공적으로 완료된 경우
                alert('성공');
            } else {
                // 서버 응답이 실패한 경우
                console.error('서버 응답 실패:', xhr.status);
            }
        }
    };
    xhr.send(JSON.stringify(searchData));
});*/
// 검색조회버튼


// searchBtn.addEventListener('click', async () => {
//     // 검색창 값
//     let searchValue = document.getElementById('search-input').value;
//     console.log(searchValue);
//
//     let searchData = {
//         searchValue: searchValue
//     };
//
//     try {
//         const response = await fetch('/api/post', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(searchData)
//         });
//
//         if (response.ok) {
//             // 서버 응답이 성공적으로 완료된 경우
//             alert('성공');
//         } else {
//             // 서버 응답이 실패한 경우
//             console.error('서버 응답 실패:', response.status);
//         }
//     } catch (error) {
//         console.error('데이터 가져오기 오류:', error);
//     }
// });

const searchBtn = document.querySelector('#search-button');
const searchInput = document.querySelector('#search-input');
const resultElement = document.querySelector('.result2');

searchBtn.addEventListener('click', async () => {
    try {
        const searchInputValue = searchInput.value;

        let apiUrl;
        console.log("searchInputValue : " + searchInputValue)

        if (searchInputValue) {
            // 게시판 종류 검색어만 입력한 경우
            apiUrl = `/admin/api/lectName/search?lectName=${searchInputValue}`;
        }

        const response = await fetch(apiUrl);
        const searchData = await response.json();
        console.log("search : " + JSON.stringify(searchData));

        grid.resetData(searchData); // 검색 결과를 그리드에 업데이트
        resultElement.textContent = `검색결과 : ${searchData.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

