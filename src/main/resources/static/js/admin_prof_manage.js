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
        },
        {
            header: '활동상태',
            sortingType: 'asc',
            name: 'active',
            sortable: true,
            editor: {
                type: 'select',
                options: {
                    listItems: [
                        {text: 'true', value: true},
                        {text: 'false', value: false}
                    ]
                }
            }
        },
        {
            header: '강좌 분류',
            sortingType: 'asc',
            name: 'profWork',
            sortable: true,
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
    console.log(JSON.stringify(grid.getRow(ev.rowKey), ['profWork', 'userName']))
    $.ajax({
        url: url,
        type: "POST",
        dataType: 'json',
        data: JSON.stringify(grid.getRow(ev.rowKey), ['profWork', 'userName']),
        success: function (response) {
            console.log(response)
        },
        error: function () {
            console.log("실패")
        }
    })
})