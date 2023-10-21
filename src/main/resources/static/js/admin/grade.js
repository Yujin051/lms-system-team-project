const grid1 = new tui.Grid({
    el: document.getElementById('grid1'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '학생명',
                name: 'userName',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '학번',
                name: 'userId',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '생년월일',
                sortingType: 'asc',
                name: 'userBirthday',
                sortable: true
            },
            {
                header: '성별',
                sortingType: 'asc',
                name: 'userGender',
                sortable: true
            },
            {
                header: '이메일',
                sortingType: 'asc',
                name: 'userEmail',
                sortable: true
            },
            {
                header: '학년',
                sortingType: 'asc',
                name: 'studGrade',
                sortable: true
            },
            {
                header: '수강생ID',
                name: 'studId',
                hidden: true
            }
        ]
    }],
    scrollX: false,
    scrollY: false,
    pageOptions: {
        useClient: true,
        perPage: 5,
    },
    "columns": [
        {
            header: '학생명',
            name: 'userName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '학번',
            name: 'userId',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '생년월일',
            sortingType: 'asc',
            name: 'userBirthday',
            sortable: true
        },
        {
            header: '성별',
            sortingType: 'asc',
            name: 'userGender',
            sortable: true
        },
        {
            header: '이메일',
            sortingType: 'asc',
            name: 'userEmail',
            sortable: true
        },
        {
            header: '학년',
            sortingType: 'asc',
            name: 'studGrade',
            sortable: true
        },
        {
            header: '수강생ID',
            name: 'studId',
            hidden: true
        }
    ]
});
console.log("grid1 : " + grid1);

// 관리자 - 전체성적관리 : 페이지 로드될때 학생정보 조회
window.addEventListener('load', async () => {
    try {
        const response = await fetch('/admin/api/grade');
        const data2 = await response.json();
        console.log(data2);
        grid1.resetData(data2);
        resultElement.textContent = `검색결과 : ${data2.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

const retrieveButton = document.querySelector('.grade_btn');
const searchNameInput = document.querySelector('.student_name_input');
const searchNumInput = document.querySelector('.student_grade_input');
const resultElement = document.querySelector('.result1');
const searchGradeInput = document.querySelector('.student_grade_select_box');

// 이름입력에 숫자가 입력되면 알림창 후 초기화
searchNameInput.addEventListener('input', function () {
    const inputValue = this.value;

    // 입력값이 숫자인 경우
    if (/^\d+$/.test(inputValue)) {
        alert('이름에는 문자만 입력 가능합니다.');
        this.value = '';
    }
});

// 버튼 클릭시 데이터 조회
retrieveButton.addEventListener('click', async () => {
    try {
        const nameKeyword = searchNameInput.value;
        const numKeyword = searchNumInput.value;
        const gradeKeyword = searchGradeInput.value; // 학년 검색어 추가
        let apiUrl;

        if (nameKeyword && numKeyword && gradeKeyword) {
            // 이름, 학번, 학년 검색 모두 입력한 경우
            apiUrl = `/admin/grade/all/api/search?idKeyword=${numKeyword}&nameKeyword=${nameKeyword}&gradeKeyword=${gradeKeyword}`;
        } else if (nameKeyword && numKeyword) {
            // 이름과 학번 검색 모두 입력한 경우
            apiUrl = `/admin/grade/userIdAndUserName/api/search?idKeyword=${numKeyword}&nameKeyword=${nameKeyword}`;
        } else if (nameKeyword && numKeyword === "") {
            // 이름 검색어만 입력한 경우
            apiUrl = `/admin/grade/userName/api/search?keyword=${nameKeyword}`;
        } else if (numKeyword && nameKeyword === "") {
            // 학번 검색어만 입력한 경우
            apiUrl = `/admin/grade/studId/api/search?keyword=${numKeyword}`;
        } else if (gradeKeyword) {
            // 학년 검색어만 입력한 경우
            apiUrl = `/admin/grade/studGrade/api/search?keyword=${gradeKeyword}`;
        } else {
            // 모든 검색어가 비어 있는 경우 전체 검색
            apiUrl = `/admin/grade/no/api/search`;
        }

        const response = await fetch(apiUrl);
        const searchData = await response.json();
        console.log("search : " + JSON.stringify(searchData));

        grid1.resetData(searchData); // 검색 결과를 그리드에 업데이트
        resultElement.textContent = `검색결과 : ${searchData.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});


let selectedRowKey = null; // 선택한 행의 키를 추적하는 변수

grid1.on('click', async (ev) => {
    const rowKey = ev.rowKey;
    const rowData = grid1.getRow(rowKey);
    const studId = rowData.studId;
    console.log("grid1StudId : " + studId);

    try {
        // memberId를 사용하여 해당 학생의 데이터를 가져오는 API 호출
        const response = await fetch(`/admin/api/gradeRecord?studId=${studId}`);
        const studentData = await response.json();

        // grid2에 데이터 설정
        grid2.resetData(studentData);
    } catch (error) {
        console.error('An error occurred:', error);
    }
});

const grid2 = new tui.Grid({
    el: document.getElementById('grid2'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '학번',
                name: 'userId',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '학기',
                name: 'semSem',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '최대수강학점',
                name: 'studMaxCr',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '현재수강학점',
                name: 'studNowCr',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '수강생ID',
                name: 'studId',
                hidden: true
            }
        ],
        "pagination": {
            "page": 1,
            "totalCount": 100
        }
    }],
    scrollX: false,
    scrollY: false,
    pageOptions: {
        useClient: true,
        perPage: 5,
    },
    "columns": [
        {
            header: '학번',
            name: 'userId',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '학기',
            name: 'semSem',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '최대수강학점',
            name: 'studMaxCr',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '현재수강학점',
            name: 'studNowCr',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '수강생ID',
            name: 'studId',
            hidden: true
        }
    ]
});

grid2.on('click', async (ev) => {
    const rowKey = ev.rowKey;
    const rowData = grid2.getRow(rowKey);
    const studId = rowData.studId;
    console.log("grid2StudId : " + studId);

    try {
        const response = await fetch(`/admin/api/findGradeByCourse?studId=${studId}`);
        const studentData = await response.json();

        // grid2에 데이터 설정
        grid3.resetData(studentData);
    } catch (error) {
        console.error('An error occurred:', error);
    }
});

const grid3 = new tui.Grid({
    el: document.getElementById('grid3'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '강좌명',
                name: 'lectName',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '평가등급',
                name: 'grade',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '출석점수',
                sortingType: 'asc',
                name: 'checkScore',
                sortable: true
            },
            {
                header: '과제점수',
                sortingType: 'asc',
                name: 'assignScore',
                sortable: true
            },
            {
                header: '시험점수',
                sortingType: 'asc',
                name: 'testScore',
                sortable: true
            },
            {
                header: '성적입력여부',
                sortingType: 'asc',
                name: 'isRecord',
                sortable: true
            }
        ],
        "pagination": {
            "page": 1,
            "totalCount": 100
        }
    }],
    scrollX: false,
    scrollY: false,
    pageOptions: {
        useClient: true,
        perPage: 5,
    },
    "columns": [
        {
            header: '강좌명',
            name: 'lectName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '평가등급',
            name: 'grade',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '출석점수',
            sortingType: 'asc',
            name: 'checkScore',
            sortable: true
        },
        {
            header: '과제점수',
            sortingType: 'asc',
            name: 'assignScore',
            sortable: true
        },
        {
            header: '시험점수',
            sortingType: 'asc',
            name: 'testScore',
            sortable: true
        },
        {
            header: '성적입력여부',
            sortingType: 'asc',
            name: 'isRecord',
            sortable: true
        }
    ]
});