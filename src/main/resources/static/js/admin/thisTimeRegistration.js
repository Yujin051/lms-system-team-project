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
            header: '강의 관리번호',
            name: 'name'
        },
        {
            header: '온라인 강의명',
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



// 'name' 열의 모든 셀을 선택
const nameCells = document.querySelectorAll('.tui-grid-cell-name');

// 선택한 모든 셀에 대해 텍스트를 가운데 정렬
nameCells.forEach(cell => {
    cell.style.textAlign = 'center';
});


const gridData2 = [
    {
        name: 'Beautiful Lies',
        artist: 'Birdy',
        release: '2016.03.26',
        type: 'Deluxe',
        genre: 'Pop'
    },
    {
        name: 'X',
        artist: 'Ed Sheeran',
        release: '2014.06.24',
        type: 'Deluxe',
        genre: 'Pop',
        _attributes: {
            disabled: true // A current row is disabled
        }
    },
    {
        name: 'Moves Like Jagger',
        release: '2011.08.08',
        artist: 'Maroon5',
        type: 'Single',
        genre: 'Pop,Rock',
        _attributes: {
            checkDisabled: true // A checkbox is disabled only
        }
    },
    {
        name: 'A Head Full Of Dreams',
        artist: 'Coldplay',
        release: '2015.12.04',
        type: 'Deluxe',
        genre: 'Rock',
        _attributes: {
            checked: true, // A checkbox is already checked while rendering
            className: {
                // Add class name on a row
                row: ['red']
            }
        }
    },
    {
        name: '19',
        artist: 'Adele',
        release: '2008.01.27',
        type: 'EP',
        genre: 'Pop,R&B',
        _attributes: {
            rowSpan: {
                // Merge rows
                artist: 3,
                genre: 2
            }
        }
    },
    {
        name: '21',
        artist: 'Adele',
        release: '2011.01.21',
        type: 'Deluxe',
        genre: 'Pop,R&B'
    },
    {
        name: '25',
        artist: 'Adele',
        release: '2015.11.20',
        type: 'EP',
        genre: 'Pop',
        _attributes: {
            className: {
                // Add class name on each columns
                column: {
                    type: ['blue'],
                    genre: ['blue']
                }
            }
        }
    }
];
//  그리드 요소에 CSS 스타일 적용
grid.el.style.width = '95rem';
grid.el.style.marginTop = '3rem';
grid.el.style.marginLeft = '92px';


const grid2 = new tui.Grid({
    el: document.getElementById('grid2'),
    data: gridData,
    scrollX: false,
    scrollY: false,
    rowHeaders: ['checkbox'],
    columns: [
        {
            header: '차시 순서',
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
    ]
});

