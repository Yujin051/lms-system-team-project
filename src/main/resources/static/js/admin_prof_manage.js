const gridData = [
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '남자', isActive : '활동중', subject : '언어' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '남자', isActive : '활동중', subject : '사회' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '여자', isActive : '활동중', subject : '언어' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '남자', isActive : '활동중', subject : '과학' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '남자', isActive : '활동중', subject : '언어' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '여자', isActive : '활동중', subject : '언어' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '남자', isActive : '활동중', subject : '사회' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '여자', isActive : '활동중', subject : '과학' },
    { profName : '강사1', tel : '010-1111-2222', email : 'prof1@prof.com', birthDate : '1972-11-05', sex : '남자', isActive : '활동중', subject : '언어' },
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
            header: '성명',
            name: 'profName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '휴대폰 번호',
            name: 'tel',
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
            header: '생년월일',
            sortingType: 'asc',
            name: 'birthDate',
            sortable: true,
        },
        {
            header: '성별',
            sortingType: 'asc',
            name: 'sex',
            sortable: true,
        },
        {
            header: '활동상태',
            sortingType: 'asc',
            name: 'isActive',
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