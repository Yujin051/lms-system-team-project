
const gridData = [
    { type: '과정1', lectureName: '강의1', elementary: '1학년', grade: '3', subject: '언어' },
    { type: '과정2', lectureName: '강의2', elementary: '2학년', grade: '3', subject: '언어' },
    { type: '과정3', lectureName: '강의3', elementary: '3학년', grade: '2', subject: '사회' },
    { type: '과정4', lectureName: '강의4', elementary: '4학년', grade: '3', subject: '사회' },
    { type: '과정5', lectureName: '강의5', elementary: '1학년', grade: '1', subject: '과학' },
    { type: '과정6', lectureName: '강의6', elementary: '2학년', grade: '2', subject: '과학' }
];
const grid = new tui.Grid({
    el: document.querySelector('#grid'),
    data: gridData,
    scrollX: false,
    scrollY: false,
    rowHeaders: ['checkbox'],
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 5, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '과정 구분',
            name: 'type',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강의명',
            name: 'lectureName',
            sortingType: 'asc',
            sortable: true,
            width : 500
        },
        {
            header: '학년',
            sortingType: 'asc',
            name: 'elementary',
            sortable: true,
            width: 220
        },
        {
            header: '학점',
            sortingType: 'asc',
            name: 'grade',
            sortable: true,
            width: 200
        },
        {
            header: '과목 구분',
            sortingType: 'asc',
            name: 'subject',
            sortable: true,
            width: 350
        }
    ]
});