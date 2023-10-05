const gridData1 = [
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
        genre: 'Pop'
    },
    {
        name: 'Moves Like Jagger',
        release: '2011.08.08',
        artist: 'Maroon5',
        type: 'Single',
        genre: 'Pop,Rock'
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
                column: {
                    type: ['blue'],
                    genre: ['blue']
                }
            }
        }
    }
];

const grid1 = new tui.Grid({
    el: document.getElementById('grid1'),
    data: gridData1,
    scrollX: false,
    scrollY: false,
    rowHeaders: ['checkbox'],
    minBodyHeight: 30,
    pageOptions: {
        useClient: true,
        perPage: 5,
    },
    columns: [
        {
            header: '게시판 번호',
            name: 'name',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '게시판 이름',
            name: 'artist',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '게시판 종류',
            name: 'type',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '답변 필요',
            name: 'release',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '운영 단위',
            name: 'genre',
            sortingType: 'desc',
            sortable: true
        }
    ]
});
