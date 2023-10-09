const gridData = [
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

// 버튼 클릭시 데이터 조회
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
const formTemplate = `<form action="/ttr/search" method="GET" class="form-inline" role="search">
<input type="text" name="keyword" class="form-control" id="search" placeholder="검색">
</form>`;

// 데이터 객체 (빈 객체)
// const data = {};

// const renderedForm = Mustache.render(template, data);

// 생성된 폼을 원하는 HTML  요소에 추가
// const formContainer = document.getElementById("")