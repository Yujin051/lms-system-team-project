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
    console.log(ev.rowKey)
    // 그리드에서 가져온 행의 키값 추출
    const valueList = Object.values(grid.getRow(ev.rowKey))

    const pId = $("#pId")
    const name =$("#name")
    const bDay = $("#birthDay")
    const gender = $("#gender")
    const tel = $("#tel")
    const addr = $("#addr")
    const mId = $("#mId")
    const email = $("#email")
    const isActive = $("#isActive")
    const aOwn = $("#aOwn")
    const bank = $("#bank")
    const acc = $("#account")
    const agency = $("#agency")


    $.ajax({
        url: url,
        type: "POST",
        async: false,
        dataType: 'json',
        data: {
            name : valueList[0],
            work : valueList[6]
        },
        success: function (response) {
            const repList = Object.values(response)
            console.log(repList)

            pId.html(repList[0])
            name.html(repList[6])
            bDay.html(repList[10])
            gender.html(repList[11])
            tel.html(repList[7])
            addr.html(repList[9])
            mId.html(repList[12])
            email.html(repList[8])
            isActive.html(String(repList[13]))
            aOwn.html(repList[2])
            bank.html(repList[3])
            acc.html(repList[4])
            agency.html(repList[1])
        },
        error: function () {
            console.log("실패")
        }
    })
})