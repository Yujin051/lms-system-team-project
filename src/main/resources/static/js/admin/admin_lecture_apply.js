
// 전체 수강신청 대상 갱좌 데이터 그리드
const gridData = [
    { lectureID: '과정1', lectureName: '강의1', subject: '언어' },
    { lectureID: '과정2', lectureName: '강의2', subject: '언어' },
    { lectureID: '과정3', lectureName: '강의3', subject: '사회' },
    { lectureID: '과정4', lectureName: '강의4', subject: '사회' },
    { lectureID: '과정5', lectureName: '강의5', subject: '과학' },
    { lectureID: '과정6', lectureName: '강의6', subject: '과학' },
    { lectureID: '과정3', lectureName: '강의3', subject: '사회' },
    { lectureID: '과정4', lectureName: '강의4', subject: '사회' },
    { lectureID: '과정5', lectureName: '강의5', subject: '과학' },
    { lectureID: '과정6', lectureName: '강의6', subject: '과학' },
    { lectureID: '과정3', lectureName: '강의3', subject: '사회' },
    { lectureID: '과정4', lectureName: '강의4', subject: '사회' },
    { lectureID: '과정5', lectureName: '강의5', subject: '과학' },
    { lectureID: '과정6', lectureName: '강의6', subject: '과학' },
    { lectureID: '과정3', lectureName: '강의3', subject: '사회' },
    { lectureID: '과정4', lectureName: '강의4', subject: '사회' },
    { lectureID: '과정5', lectureName: '강의5', subject: '과학' },
    { lectureID: '과정6', lectureName: '강의6', subject: '과학' }
];
const grid = new tui.Grid({
    el: document.querySelector('#grid'),
    data: gridData,
    scrollX: false,
    scrollY: false,
    rowHeaders: ['checkbox'],
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 10, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '강의 ID',
            name: 'lectureID',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강의명',
            name: 'lectureName',
            sortingType: 'asc',
            sortable: true,
        },
        {
            header: '과목명',
            sortingType: 'asc',
            name: 'subject',
            sortable: true,
        }
    ]
});

// 수강신청 한 학생 데이터 그리드

const gridData2 = [
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'},
    {studentName : '학생1', studentID : 'SE2023111', email : 'email@email.com', subject : '과목1', sex : '성별'}
]

const grid2 = new tui.Grid({
    el: document.querySelector('#grid2'),
    data: gridData2,
    scrollX: false,
    scrollY: false,
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 5, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '학생명',
            name: 'studentName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '학번',
            name: 'studentID',
            sortingType: 'asc',
            sortable: true,
        },
        {
            header: '이메일',
            sortingType: 'asc',
            name: 'email',
            sortable: true,
        },
        {
            header: '과목명',
            sortingType: 'asc',
            name: 'subject',
            sortable: true,
        },
        {
            header: '성별',
            sortingType: 'asc',
            name: 'sex',
            sortable: true,
        }

    ]
})