// 과제정보
const gridData1 = [
    {
        assignId: 174246785,
        assignName: '혼자 공부하는 자바',
        assignStart: '2023-09-01',
        assignEnd: '2023-09-30',
        isActive: '마감',
        assignEdit: 3
    },
    {
        assignId: 174246786,
        assignName: '혼자 공부하는 자바',
        assignStart: '2023-09-01',
        assignEnd: '2023-09-30',
        isActive: '진행중',
        assignEdit: 0
    }
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
        perPage: 5, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '과제번호',
            name: 'assignId',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '과제명',
            name: 'assignName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '제출시작일시',
            sortingType: 'desc',
            name: 'assignStart',
            sortable: true
        },
        {
            header: '제출마감일시',
            sortingType: 'desc',
            name: 'assignEnd',
            sortable: true
        },
        {
            header: '진행상태',
            sortingType: 'desc',
            name: 'isActive',
            sortable: true
        },
        {
            header: '과제수정',
            sortingType: 'desc',
            name: 'assignEdit',
            sortable: true
        }
    ]
});

// 학생정보
const gridData2 = [
    {
        studId: 6043158,
        userName: '김호우',
        submitDatetime: '2023-09-02',
        isSubmit: '제출',
        submitCont: '과제제출정보버튼',
        feedback: '완료'
    }
];
const grid2 = new tui.Grid({
    el: document.querySelector('#grid2'),
    data: gridData2,
    scrollX: false,
    scrollY: false,
    minBodyHeight: 30,
    rowHeaders: ['rowNum'],
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 5, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '학번',
            name: 'studId',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '성명',
            name: 'userName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '제출일시',
            sortingType: 'desc',
            name: 'submitDatetime',
            sortable: true
        },
        {
            header: '제출구분',
            sortingType: 'desc',
            name: 'isSubmit',
            sortable: true
        },
        {
            header: '과제제출정보',
            sortingType: 'desc',
            name: 'submitCont',
            sortable: true
        },
        {
            header: '피드백',
            sortingType: 'desc',
            name: 'feedback',
            sortable: true
        }
    ]
});