const grid = new tui.Grid({
    el: document.getElementById('grid'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '학번',
                name: 'userId',
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
                header: '성별',
                sortingType: 'asc',
                name: 'userGender',
                sortable: true,
                hidden: true,
            },
            {
                header: '전화번호',
                sortingType: 'asc',
                name: 'userPhoneNum',
                sortable: true,
            },
            {
                header: '주소',
                sortingType: 'asc',
                name: 'userAddr',
                sortable: true,
                hidden: true,
            },
            {
                header: '수강생 번호',
                sortingType: 'asc',
                name: 'studId',
                sortable: true,
                hidden: true,
            },
            {
                header: '이메일',
                sortingType: 'asc',
                name: 'userEmail',
                sortable: true,
            },
            {
                header: '학점',
                sortingType: 'asc',
                name: 'studNowCr',
                sortable: true,
                hidden: true,
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
            header: '성별',
            sortingType: 'asc',
            name: 'userGender',
            sortable: true,
            hidden: true,
        },
        {
            header: '전화번호',
            sortingType: 'asc',
            name: 'userPhoneNum',
            sortable: true,
        },
        {
            header: '주소',
            sortingType: 'asc',
            name: 'userAddr',
            sortable: true,
            hidden: true,
        },
        {
            header: '수강생 번호',
            sortingType: 'asc',
            name: 'studId',
            sortable: true,
            hidden: true,
        },
        {
            header: '이메일',
            sortingType: 'asc',
            name: 'userEmail',
            sortable: true,
        },
        {
            header: '학점',
            sortingType: 'asc',
            name: 'studNowCr',
            sortable: true,
            hidden: true,
        }
    ],
});
console.log(grid)
grid.on('click', async (ev) => {
    const rowData = grid.getRow(ev.rowKey);

    // 각 열에 대한 데이터 추출
    const userId = rowData.userId; //학번
    const studId = rowData.studId; //수강생번호
    const userName = rowData.userName; //성명
    const userBirthday = rowData.userBirthday; //생년월일
    const userPhoneNum = rowData.userPhoneNum; //전화번호
    const userEmail = rowData.userEmail; //이메일
    const userStudNowCr = rowData.studNowCr; //학점
    const userGender = rowData.userGender; //성별
    const userAddr = rowData.userAddr; //주소
    console.log("studId : " + rowData.studId);


    const userIdSpan = document.querySelector('.user_id');
    const studIdSpan = document.querySelector('.user_stud_id');
    const userNameSpan = document.querySelector('.user_name');
    const userBirthdaySpan = document.querySelector('.user_birthday');
    const userPhoneNumSpan = document.querySelector('.user_phonenum');
    const userEmailSpan = document.querySelector('.user_email');
    const userGenderSpan = document.querySelector('.user_gender');
    const userAddrSpan = document.querySelector('.user_addr');
    const userStudNowCrSpan = document.querySelector('.user_stud_now_cr');


    userIdSpan.style.display = 'block';
    studIdSpan.style.display = 'block';
    userNameSpan.style.display = 'block';
    userBirthdaySpan.style.display = 'block';
    userPhoneNumSpan.style.display = 'block';
    userEmailSpan.style.display = 'block';
    userStudNowCrSpan.style.display = 'block';
    userGenderSpan.style.display = 'block';
    userAddrSpan.style.display = 'block';


    // 데이터 넣기
    userIdSpan.textContent = userId;
    studIdSpan.textContent = studId;
    userNameSpan.textContent = userName;
    userBirthdaySpan.textContent = userBirthday;
    userPhoneNumSpan.textContent = userPhoneNum;
    userEmailSpan.textContent = userEmail;
    userStudNowCrSpan.textContent = userStudNowCr;
    userGenderSpan.textContent = userGender;
    userAddrSpan.textContent = userAddr;

});


const retrieveButton = document.querySelector('.stud_select_btn');
const searchNameInput = document.querySelector('.lName');
const searchNumInput = document.querySelector('.lNum');
const resultElement = document.querySelector('.result');

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
        } else if(numKeyword === "" && nameKeyword === "") {
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


