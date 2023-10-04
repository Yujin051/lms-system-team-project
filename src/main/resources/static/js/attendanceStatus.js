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
    data: gridData,
    scrollX: false,
    scrollY: false,
    columns: [
        {
            header: '개성강좌번호',
            name: 'name'
        },
        {
            header: '강좌명',
            name: 'artist'
        },
        {
            header: '과목명',
            name: 'type'
        },
        {
            header: '강의 시작 일시',
            name: 'release'
        },
        {
            header: '강의 종료 일시',
            name: 'genre'
        },
        {
            header: '강의 운영 상태',
            name: 'lectureStatus'
        }
    ]
});

//  그리드 요소에 CSS 스타일 적용
grid.el.style.width = '95rem';
grid.el.style.marginTop = '3rem';
grid.el.style.marginLeft = '92px';
gridData.style.textAlign = 'center';

// 'name' 열의 모든 셀을 선택
const nameCells = document.querySelectorAll('.tui-grid-cell-name');

// 선택한 모든 셀에 대해 텍스트를 가운데 정렬
nameCells.forEach(cell => {
    cell.style.textAlign = 'center';
});
