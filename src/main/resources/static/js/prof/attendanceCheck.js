const gridData1 = [
    { name: '스폰지밥', email: 'asd@naver.com', attendance: 6, tardy: 0, absent: 2 },
    { name: '징징이', email: 'qweq@naver.com', attendance: 5, tardy: 0, absent: 3 },
    { name: '뚱이', email: 'tqwe@naver.com', attendance: 4, tardy: 0, absent: 4 },
    { name: '집게사장', email: 'qqq@naver.com', attendance: 3, tardy: 6, absent: 0 },
    { name: '시민1', email: 'aaa@naver.com', attendance: 2, tardy: 2, absent: 0 },
    { name: '시민2', email: 'sss@naver.com', attendance: 7, tardy: 1, absent: 7 },
    { name: '시민3', email: 'ccc@naver.com', attendance: 8, tardy: 1, absent: 1 },
    { name: '시민4', email: 'www@naver.com', attendance: 1, tardy: 2, absent: 2 },
    { name: '시민5', email: 'ttt@naver.com', attendance: 1, tardy: 2, absent: 1 },
    { name: '시민6', email: 'eee@naver.com', attendance: 2, tardy: 7, absent: 1 },
    { name: '시민7', email: 'ggg@naver.com', attendance: 3, tardy: 8, absent: 0 }
];
const grid1 = new tui.Grid({
    el: document.querySelector('#grid1'),
    data: gridData1,
    scrollX: false,
    scrollY: false,
    minBodyHeight: 30,
    rowHeaders: ['rowNum'],
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 10, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '학생명',
            name: 'name',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '이메일',
            name: 'email',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '출석',
            sortingType: 'desc',
            name: 'attendance',
            sortable: true
        },
        {
            header: '지각',
            sortingType: 'desc',
            name: 'tardy',
            sortable: true
        },
        {
            header: '결석',
            sortingType: 'desc',
            name: 'absent',
            sortable: true
        }
    ]
});