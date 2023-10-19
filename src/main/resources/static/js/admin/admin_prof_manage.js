const dataSource = {
    api: {
        readData: {url: '/admin/getproflist', method: 'GET'}
    }
}

const grid = new tui.Grid({
    el: document.querySelector('#grid'),
    data: dataSource,
    scrollX: false,
    scrollY: false,
    pageOptions: {
        useClient: true,
        perPage: 5 // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '성명',
            name: 'userName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '휴대폰 번호',
            name: 'userPhoneNum',
            sortingType: 'asc',
            sortable: true,
        },
        {
            header: '이메일',
            sortingType: 'asc',
            name: 'userEmail',
            sortable: true,
        },
        {
            header: '생년월일',
            sortingType: 'asc',
            name: 'userBirthDay',
            sortable: true,
        },
        {
            header: '성별',
            sortingType: 'asc',
            name: 'userGender',
            sortable: true,
            formatter: (props) => {
                // 성별 변경하는 포매터
                const value = props.value;
                if (value === 'MALE') {
                    return '남성';
                } else if (value === 'FEMALE') {
                    return '여성';
                } else {
                    return '';
                }
            }
        },
        {
            header: '활동상태',
            sortingType: 'asc',
            name: 'active',
            sortable: true,
            formatter: (props) => {
                // boolean값 변경하는 포매터
                const value = props.value;
                if (value === true) {
                    return '활둥중';
                } else if (value === false) {
                    return '활동중이지 않음';
                } else {
                    return '';
                }
            }

        },
        {
            header: '강좌 분류',
            sortingType: 'asc',
            name: 'profWork',
            sortable: true
        }
    ],
    minBodyHeight: 30
});

// 조회 버튼 클릭 시 설정된 검색 조건으로 검색 수행하여 그리드에 출력하는 함수
const searchBtn = document.querySelector("#searchBtn")
searchBtn.addEventListener('click', () => {
    const url = "/admin/getproflist"
    const subject = $("#subject :selected").text()
    const active = $("#active :selected").val()
    const name = $(".lName").val()
    $.ajax({
        url: url,
        type: "POST",
        async: false,
        data: {
            subject: subject,
            active: active,
            name: name
        },
        success: function(response) {
            grid.resetData(response)
            console.log("조회 성공")
        },
        error: function () {
            alert("조회 오류")
        }
    })
})

// 셀 클릭시 해당 행 상세정보 출력
grid.on('click', (ev) => {
    const url = "/admin/getdetail"
    // 그리드에서 가져온 행의 키값 추출
    const toName = grid.getValue(ev.rowKey, 'userName')
    const toWork = grid.getValue(ev.rowKey, 'profWork')

    console.log(toName, toWork)

    const pId = document.querySelector("#pId");
    const name = document.querySelector("#name");
    const bDay = document.querySelector("#birthDay");
    const gender = document.querySelector("#gender");
    const tel = document.querySelector("#tel");
    const addr = document.querySelector("#addr");
    const mId = document.querySelector("#mId");
    const email = document.querySelector("#email");
    const isActive = document.querySelector("#isActive");
    const aOwn = document.querySelector("#aOwn");
    const bank = document.querySelector("#bank");
    const acc = document.querySelector("#account");
    const agency = document.querySelector("#agency");


    $.ajax({
        url: url,
        type: "POST",
        async: false,
        dataType: 'json',
        data: {
            name : toName,
            work : toWork
        },
        success: function (response) {
            let boolean;
            if(response.isActive === true) {
                boolean = "활동중"
            } else {
                boolean = "활둥중이 아님"
            }

            pId.textContent = response.id;
            name.textContent = response.userName;
            bDay.textContent = response.userBirthDay;
            gender.textContent = response.userGender;
            tel.textContent = response.userPhoneNum;
            addr.textContent = response.userAddr;
            mId.textContent = response.memberId;
            email.textContent = response.userEmail;
            isActive.textContent = boolean;
            aOwn.textContent = response.profAcOwner;
            bank.textContent = response.profBank;
            acc.textContent = response.profAccount;
            agency.textContent = response.profAgency;
        },
        error: function () {
            console.log("실패")
        }
    })
})

// 개수 업데이트 로직
const count = document.querySelector("#result")
// 그리드 로딩됐을 때
grid.on('onGridMounted', ()=> {
    let number = grid.getRowCount();
    count.innerText = '검색 결과 : ' + number + '건'
})

grid.on('onGridUpdated', () => {
    let number = grid.getRowCount();
    count.innerText = '검색 결과 : ' + number + '건'
})
