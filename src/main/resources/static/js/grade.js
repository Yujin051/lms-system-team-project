const gridData = [
    { name: '스폰지밥', studentId: 'S1511E0001', birthday: '1980-06-01', sex: '남', email: 'asd@naver.com', grade: '1학년'},
    { name: '뚱이', studentId: 'S1511E0002', birthday: '1980-06-01', sex: '남', email: 'asd@naver.com', grade: '2학년' },
    { name: '징징이', studentId: 'S1511E0003', birthday: '1980-06-01', sex: '남', email: 'asd@naver.com', grade: '1학년' },
    { name: '다람이', studentId: 'S1511E0003', birthday: '1980-06-01', sex: '여', email: 'asd@naver.com', grade: '1학년' },
    { name: '다람이', studentId: 'S1511E0003', birthday: '1980-06-01', sex: '여', email: 'asd@naver.com', grade: '1학년' },
    { name: '다람이', studentId: 'S1511E0003', birthday: '1980-06-01', sex: '여', email: 'asd@naver.com', grade: '1학년' }
];
const grid = new tui.Grid({
    el: document.querySelector('#grid1'),
    data: gridData,
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
            header: '학생명',
            name: 'name',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '학번',
            name: 'studentId',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '생년월일',
            sortingType: 'desc',
            name: 'birthday',
            sortable: true
        },
        {
            header: '성별',
            sortingType: 'desc',
            name: 'sex',
            sortable: true
        },
        {
            header: '이메일',
            sortingType: 'desc',
            name: 'email',
            sortable: true
        },
        {
            header: '학년',
            sortingType: 'asc',
            name: 'grade',
            sortable: true
        }
    ]
});

const gridData2 = [
    { name: '스폰지밥', studentId: 'S1511E0001', birthday: '1980-06-01', sex: '남', email: 'asd@naver.com', grade: '1학년'}
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
            header: '년도',
            name: 'name',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '학기',
            name: 'studentId',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '출석률',
            sortingType: 'desc',
            name: 'birthday',
            sortable: true
        },
        {
            header: '학번',
            sortingType: 'desc',
            name: 'sex',
            sortable: true
        },
        {
            header: '현재수강학점',
            sortingType: 'desc',
            name: 'email',
            sortable: true
        }
    ]
});

const gridData3 = [
    { lectId: '11', name: '홍길동', birthday: '1980-06-01', sex: '남', email: 'asd@naver.com', grade: '1학년'}
];
const grid3 = new tui.Grid({
    el: document.querySelector('#grid3'),
    data: gridData3,
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
            header: '강좌번호',
            name: 'lectId',
            sortingType: 'desc',
            sortable: true
        },
        {
            header: '담당강사',
            name: 'name',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강좌명',
            sortingType: 'desc',
            name: 'birthday',
            sortable: true
        },
        {
            header: '강좌시작일',
            sortingType: 'desc',
            name: 'sex',
            sortable: true
        },
        {
            header: '강좌종료일',
            sortingType: 'desc',
            name: 'email',
            sortable: true
        },
        {
            header: '강좌총점수',
            sortingType: 'asc',
            name: 'grade',
            sortable: true
        },
        {
            header: '학점',
            sortingType: 'asc',
            name: 'grade',
            sortable: true
        }
    ]
});