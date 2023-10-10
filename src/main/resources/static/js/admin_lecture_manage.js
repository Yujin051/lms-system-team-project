const dataSource = {
    api: {
        readData: {url: "/getlecturelist", method: "GET"},
        modifyData:{url: "/savelecturedata", method: "PUT"}
    }
}
const grid = new tui.Grid({
    el: document.querySelector('#grid'),
    data: dataSource,
    scrollX: false,
    scrollY: false,
    minBodyHeight: 30,
    rowHeaders: ['checkbox'],
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 5, // 페이지당 표시할 항목 수
    },
    columns: [
        {
            header: '운영 상태',
            name: 'active',
            sortingType: 'asc',
            sortable: true,
            formatter: (props) => {
                const value = props.value;
                if (value === true) {
                    return '운영중'; // true인 경우 "운영중"으로 표시
                } else if (value === false) {
                    return '마감됨'; // false인 경우 "마감됨"으로 표시
                } else {
                    return ''; // null 또는 다른 값인 경우 공백으로 표시
                }
            }
        },
        {
            header: '강의명',
            name: 'lectName',
            sortingType: 'asc',
            sortable: true,
            width: 500
        },
        {
            header: '학년',
            sortingType: 'asc',
            name: 'lectElem',
            sortable: true,
            width: 220,
            formatter: (props) => {
                // 학년 변경하는 포매터
                const value = props.value;
                if (value === 1) {
                    return '1학년';
                } else if (value === 2) {
                    return '2학년';
                } else if (value === 3) {
                    return '3학년';
                } else if (value === 4) {
                    return '4학년';
                } else {
                    return '';
                }
            }
        },
        {
            header: '학점',
            sortingType: 'asc',
            name: 'lectCredit',
            sortable: true,
            width: 200
        },
        {
            header: '과목 구분',
            sortingType: 'asc',
            name: 'lectSubject',
            sortable: true,
            width: 350
        }
    ]
});

// 각 요소 선택자 선언
const lectName = document.querySelector("#lectName")
const lectGrade = document.querySelector("#lectGrade")
const credit = document.querySelector("#lectCredit")
const subject = document.querySelector("#lectSubject")
const lectId = document.querySelector("#lectId")
const start = document.querySelector("#startDateInput")
const end = document.querySelector("#endDateInput")
const profName = document.querySelector("#profName")
const active = document.querySelector("#isActive")

// 행 클릭했을 때 상세정보 출력하기
grid.on('click', (ev) => {
    const valueList = Object.values(grid.getRow(ev.rowKey))
    const url = "/getlecturedetail"
    // console.log(valueList.toString())

    $.ajax({
        url: url,
        data: {
            name: valueList[1]
        },
        success: function (response) {
            const details = Object.values(response)
            // console.log(details.toString())

            // 강좌 이름 undefined 처리
            if (details[0] === null || details[0] === undefined) {
                lectName.value = "강좌 이름을 입력해주세요."
            } else {
                lectName.value = details[0]
            }
            // 강좌 분류(과목) undefined 처리
            if (details[3] === null || details[3] === undefined) {
                subject.value = "과목을 입력해주세요."
            } else {
                subject.value = details[3]
            }
            // 강좌 번호(프라이머리키) 처리
            if (details[4] === null || details[4] === undefined) {
                lectId.value = "저장 후 자동 생성됩니다."
            } else {
                lectId.value = details[4]
            }
            if (details[7] === null || details[7] === undefined) {
                profName.value = "강사 이름을 입력해주세요."
            } else {
                profName.value = details[7]
            }

            lectGrade.value = details[1]
            credit.value = details[2]
            start.value = details[5]
            end.value = details[6]
            active.value = details[8]
        },
        error: function () {
            alert("데이터 불러오기 오류")
        }
    })
})

// 각각의 버튼 변수 선언
const newBtn = document.querySelector("#create")
const searchBtn = document.querySelector("#search")
const deleteBtn = document.querySelector("#delete")
const saveBtn = document.querySelector("#save")

// 조건 값 변수 선언
const year = document.querySelector("#lectYear")
const sem = document.querySelector("#lectSem")
const lectActive = document.querySelector("#lectActive")
const lectSub = document.querySelector("#lectSubj")
const nameSearch = document.querySelector(".lName")
const lectElem = document.querySelector("#lectElem")

// 신규 버튼 눌렀을 때 새로운 그리드 행 생성
newBtn.addEventListener('click', () => {
    // 새로운 행 추가
    grid.appendRow();
})

// 체크된 행 삭제
deleteBtn.addEventListener('click', () => {
    // 체크된 행 키 값의 배열
    const rows = grid.getCheckedRowKeys()
    // 해당 행들 삭제
    grid.removeRows(rows)
})

// 설정된 조건으로 해당하는 데이터 검색
searchBtn.addEventListener('click', () => {
    const url = "/searchlectlist"
    $.ajax({
        url: url,
        type: "GET",
        data: {
            year: year.value,
            sem: sem.value,
            active: lectActive.value,
            subject: lectSub.value,
            name: nameSearch.value,
            elem: lectElem.value
        },
        success: function (response) {
            console.log(response)
            grid.resetData(response)
        },
        error: function () {
            alert("데이터 불러오기 오류")
        }
    })
})

// 수정, 추가는 ajax 이용하여 직접 처리
saveBtn.addEventListener('click', () => {
    const url = "/savelecturedata"
    // 서버로 보낼 data 지정
    let id = lectId.value
    // id 값은 신규 생성 시 할당되지 않으므로 null 처리
    id = id || "";

    const data = {
        lectName:lectName.value,
        lectElem: lectGrade.value,
        lectCredit: credit.value,
        lectSubject: subject.value,
        lectId: id,
        lectStart: start.value,
        lectEnd: end.value,
        userName: profName.value,
        active: active.value
    }

    const modifiedData = JSON.stringify(data);

    $.ajax({
        url: url,
        type: 'PUT',
        contentType: "application/json",
        dataType: 'json',
        data: modifiedData,
        success: function () {

        },
        error: function () {

        }
    })

})

// 행 삭제(데이터 삭제)는 그리드 이용하여 처리

