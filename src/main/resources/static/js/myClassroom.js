const gridData = [
    {
        name: '5564',
        artist: '과제테스트',
        type: '2023-09-13 00:00 ~ 2023-09-22 23:59',
        release: '진행중',
        genre: '미제출'
    },
    {
        name: '5564',
        artist: '과제테스트',
        type: '2023-09-13 00:00 ~ 2023-09-22 23:59',
        release: '진행중',
        genre: '미제출'
    },
    {
        name: '5564',
        artist: '과제테스트',
        type: '2023-09-13 00:00 ~ 2023-09-22 23:59',
        release: '진행중',
        genre: '미제출'
    },
    {
        name: '5564',
        artist: '과제테스트',
        type: '2023-09-13 00:00 ~ 2023-09-22 23:59',
        release: '진행중',
        genre: '미제출'
    },
    {
        name: '5564',
        artist: '과제테스트',
        type: '2023-09-13 00:00 ~ 2023-09-22 23:59',
        release: '진행중',
        genre: '미제출'
    }
    // 다른 데이터 항목 추가 가능
];

const grid = new tui.Grid({
    el: document.getElementById('grid'),
    data: gridData,
    scrollX: false,
    scrollY: true,
    columns: [
        {
            header: '과제ID',
            name: 'name'
        },
        {
            header: '과제명',
            name: 'artist'
        },
        {
            header: '과제기간',
            name: 'type'
        },
        {
            header: '진행상태',
            name: 'release'
        },
        {
            header: '제출구분',
            name: 'genre'
        }
    ]
});

//  그리드 요소에 CSS 스타일 적용
grid.el.style.width = '95rem';
grid.el.style.marginTop = '1rem';
grid.el.style.marginLeft = '200px';
gridData.style.textAlign = 'center';

// 'name' 열의 모든 셀을 선택
const nameCells = document.querySelectorAll('.tui-grid-cell-name');

// 선택한 모든 셀에 대해 텍스트를 가운데 정렬
nameCells.forEach(cell => {
    cell.style.textAlign = 'center';
});
