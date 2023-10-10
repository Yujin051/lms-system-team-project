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
                name: 'studId',
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
                header: '현재수강학점',
                name: 'studNowCr',
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
            name: 'studId',
            sortingType: 'asc',
            sortable: true,
        },
        {
            header: '생년월일',
            sortingType: 'asc',
            name: 'userBirthday',
            sortable: true,
        },
        {
            header: '성별',
            sortingType: 'asc',
            name: 'userGender',
            sortable: true,
        },
        {
            header: '이메일',
            sortingType: 'asc',
            name: 'userEmail',
            sortable: true,
        },
        {
            header: '학년',
            sortingType: 'asc',
            name: 'studGrade',
            sortable: true,
        },
        {
            header: '현재수강학점',
            name: 'studNowCr',
            hidden: true
        }
    ]
});
console.log("grid1 : " + grid1);

const retrieveButton = document.querySelector('.grade_btn');
const searchNameInput = document.querySelector('.student_name_input');
const searchNumInput = document.querySelector('.student_grade_input');
const resultElement = document.querySelector('.result1');
const searchGradeInput = document.querySelector('.student_grade_select_box');

// 학번입력에 문자가 입력되면 알림창 후 초기화
searchNumInput.addEventListener('input', function () {
    const inputValue = this.value;

    // 숫자가 아닌 문자가 입력된 경우
    if (!/^\d*$/.test(inputValue)) {
        alert('학번에는 숫자만 입력 가능합니다.');
        this.value = '';
    }
});

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
            apiUrl = `/admin/grade/all/api/search?idKeyword=${numKeyword}&nameKeyword=${nameKeyword}`;
        } else if (nameKeyword) {
            // 이름 검색어만 입력한 경우
            apiUrl = `/admin/grade/userName/api/search?keyword=${nameKeyword}`;
        } else if (numKeyword) {
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

let selectedRowKey = null; // 선택한 행의 키를 추적하는 변수

grid1.on('click', async (ev) => {
    const rowKey = ev.rowKey;

    // 선택한 행이 변경된 경우, grid2의 데이터를 초기화
    if (selectedRowKey !== rowKey) {
        grid2.resetData([]);
        selectedRowKey = rowKey;
    }

    // 클릭한 행의 데이터 가져오기
    const rowData = grid1.getRow(rowKey);

    // grid2에 중복되지 않는 데이터 추가 또는 설정
    const grid2Data = grid2.getData();

    // 중복을 체크하여 추가
    if (!grid2Data.some(item => item.studId === rowData.studId)) {
        grid2Data.push(rowData);
        grid2.resetData(grid2Data);
    }
});


const grid2 = new tui.Grid({
    el: document.getElementById('grid2'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '학번',
                name: 'studId',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '등급',
                name: 'semGrade',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '년도',
                sortingType: 'asc',
                name: 'semYear',
                sortable: true
            },
            {
                header: '학기',
                sortingType: 'asc',
                name: 'semSem',
                sortable: true
            },
            {
                header: '현재수강학점',
                sortingType: 'asc',
                name: 'studNowCr',
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
            header: '학번',
            name: 'studId',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '등급',
            name: 'semGrade',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '년도',
            sortingType: 'asc',
            name: 'semYear',
            sortable: true
        },
        {
            header: '학기',
            sortingType: 'asc',
            name: 'semSem',
            sortable: true
        },
        {
            header: '현재수강학점',
            sortingType: 'asc',
            name: 'studNowCr',
            sortable: true
        }
    ]
});

const grid3 = new tui.Grid({
    el: document.getElementById('grid3'),
    "result": true,
    data: [{
        "contents": [
            {
                header: ' 강좌번호',
                name: 'lectId',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '담당강사',
                name: 'userName',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '강좌명',
                sortingType: 'asc',
                name: 'lectName',
                sortable: true
            },
            {
                header: '강좌시작일',
                sortingType: 'asc',
                name: 'lectStart',
                sortable: true
            },
            {
                header: '강좌종료일',
                sortingType: 'asc',
                name: 'lectEnd',
                sortable: true
            },
            {
                header: '강좌상태',
                sortingType: 'asc',
                name: 'isActive',
                sortable: true
            },
            {
                header: '강좌학점',
                sortingType: 'asc',
                name: 'lectCredit',
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
            header: ' 강좌번호',
            name: 'lectId',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '담당강사',
            name: 'userName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강좌명',
            sortingType: 'asc',
            name: 'lectName',
            sortable: true
        },
        {
            header: '강좌시작일',
            sortingType: 'asc',
            name: 'lectStart',
            sortable: true
        },
        {
            header: '강좌종료일',
            sortingType: 'asc',
            name: 'lectEnd',
            sortable: true
        },
        {
            header: '강좌상태',
            sortingType: 'asc',
            name: 'isActive',
            sortable: true
        },
        {
            header: '강좌학점',
            sortingType: 'asc',
            name: 'lectCredit',
            sortable: true
        }
    ]
});

grid2.on('click', async (ev) => {
    const rowKey = ev.rowKey;
    const rowData = grid2.getRow(rowKey);
    const studId = rowData.studId; // 선택한 행의 studId 가져오기
    console.log("studId : " + studId);

    try {
        // studId를 사용하여 해당 학생의 데이터를 가져오는 API 호출
        $.ajax({
            url: `/admin/api/gradeByCourse?studId=${studId}`,
            method: 'GET',
            dataType: 'json',
            success: function (studentData) {
                console.log("studentData : " + studentData.lectCredit);

                // grid3에 데이터 설정
                const grid3Data = studentData;
                grid3.resetData(grid3Data);
                console.log("grid3 : " + JSON.stringify(grid3Data)); // JSON.stringify를 사용하여 객체를 문자열로 변환
            },
            error: function (error) {
                // 오류 처리
                console.error('An error occurred:', error);
            }
        });
    } catch (error) {
        // 오류 처리
        console.error('An error occurred:', error);
    }
});