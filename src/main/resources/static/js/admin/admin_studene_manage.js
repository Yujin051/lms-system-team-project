const gridData = [
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' },
    { studentID : '202301050', studentName : '학생1', birthDate : '1994-05-17', tel : '010-1111-2222', email : 'student@student.com' }
];

const grid = new tui.Grid({
    el: document.querySelector('#grid'),
    data: gridData,
    scrollX: false,
    scrollY: false,
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 5, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '학번',
            name: 'studentID',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '성명',
            name: 'studentName',
            sortingType: 'asc',
            sortable: true,
        },
        {
            header: '생년월일',
            sortingType: 'asc',
            name: 'birthdate',
            sortable: true,
        },
        {
            header: '전화번호',
            sortingType: 'asc',
            name: 'tel',
            sortable: true,
        },
        {
            header: '이메일',
            sortingType: 'asc',
            name: 'email',
            sortable: true,
        }
    ]
});