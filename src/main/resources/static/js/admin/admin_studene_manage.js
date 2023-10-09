const grid = new tui.Grid({
    el: document.getElementById('grid'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '학번',
                name: 'studId',
                sortingType: 'asc',
                sortable: true,
            },
            {
                header: '성명',
                name: 'userName',
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
                header: '전화번호',
                sortingType: 'asc',
                name: 'userPhoneNum',
                sortable: true,
            },
            {
                header: '이메일',
                sortingType: 'asc',
                name: 'userEmail',
                sortable: true,
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
            sortable: true,
        },
        {
            header: '성명',
            name: 'userName',
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
            header: '전화번호',
            sortingType: 'asc',
            name: 'userPhoneNum',
            sortable: true,
        },
        {
            header: '이메일',
            sortingType: 'asc',
            name: 'userEmail',
            sortable: true,
        }
    ],
});
console.log(grid)
grid.on('click', async (ev) => {
    const rowData = grid.getRow(ev.rowKey);

    // 각 열에 대한 데이터 추출
    const studId = rowData.studId;
    const userName = rowData.userName;
    const userBirthday = rowData.userBirthday;
    const userPhoneNum = rowData.userPhoneNum;
    const userEmail = rowData.userEmail;

    const studIdSpan = document.querySelector('.stud_id');
    const userNameSpan = document.querySelector('.user_name');
    const userBirthdaySpan = document.querySelector('.user_birthday');
    const userPhoneNumSpan = document.querySelector('.user_phonenum');
    const userEmailSpan = document.querySelector('.user_email');

    studIdSpan.style.display = 'block';
    userNameSpan.style.display = 'block';
    userBirthdaySpan.style.display = 'block';
    userPhoneNumSpan.style.display = 'block';
    userEmailSpan.style.display = 'block';

    // 데이터 넣기
    studIdSpan.textContent = studId;
    userNameSpan.textContent = userName;
    userBirthdaySpan.textContent = userBirthday;
    userPhoneNumSpan.textContent = userPhoneNum;
    userEmailSpan.textContent = userEmail;


});


const retrieveButton = document.querySelector('.stud_select_btn');
const searchNameInput = document.querySelector('.lName');
const searchNumInput = document.querySelector('.lNum');
const resultElement = document.querySelector('.result');

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
        let apiUrl;

        if (nameKeyword && numKeyword) {
            // 이름과 학번 검색 모두 입력한 경우
            apiUrl = `/admin/studentmanage/all/api/search?idKeyword=${numKeyword}&nameKeyword=${nameKeyword}`;
        } else if (nameKeyword) {
            // 이름 검색어만 입력한 경우
            apiUrl = `/admin/studentmanage/userName/api/search?keyword=${nameKeyword}`;
        } else if (numKeyword) {
            // 학번 검색어만 입력한 경우
            apiUrl = `/admin/studentmanage/studId/api/search?keyword=${numKeyword}`;
        } else {
            // 이름과 학번 모두 비어 있는 경우 전체 검색
            apiUrl = `/admin/studentmanage/no/api/search?idKeyword=${numKeyword}&nameKeyword=${nameKeyword}`;
        }

        const response = await fetch(apiUrl);
        const searchData = await response.json();
        console.log("search : " + JSON.stringify(searchData));

        grid.resetData(searchData); // 검색 결과를 그리드에 업데이트
        const resultElement = document.querySelector('.result');
        resultElement.textContent = `검색결과 : ${searchData.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

// 관리자 - 학생관리 : 페이지 로드될때 학생정보 조회
window.addEventListener('load', async () => {
    try {
        const response = await fetch('/admin/api/st');
        const data2 = await response.json();
        console.log(data2);
        grid.resetData(data2);
        resultElement.textContent = `검색결과 : ${data2.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});


