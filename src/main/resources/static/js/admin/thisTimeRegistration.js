const grid = new tui.Grid({
    el: document.getElementById('grid'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '차시순서',
                name: 'lectId'
            },
            {
                header: '차시 명',
                name: 'lectName'
            },
            {
                header: '강좌명',
                name: 'lectSubject'
            },
            {
                header: '강의 시작 일시',
                name: 'lectStart'
            },
            {
                header: '강의 종료 일시',
                name: 'lectEnd'
            },
            {
                header: '운영 상태',
                name: 'isActive'
            },
            {
                header: '강좌 ID',
                name: 'lectId',
                hidden: true
            },
        ],
        "pagination": {
            "page": 1,
            "totalCount": 100
        }
    }],
    scrollX: false,
    scrollY: false,
    rowHeaders: ['rowNum'],
    pageOptions: {
        useClient: true,
        perPage: 5,
    },
    "columns": [
        {
            header: '강좌관리번호',
            name: 'lectId'
        },
        {
            header: '강좌명',
            name: 'lectName'
        },
        {
            header: '강좌분류',
            name: 'lectSubject'
        },
        {
            header: '강좌 시작 일시',
            name: 'lectStart'
        },
        {
            header: '강좌 종료 일시',
            name: 'lectEnd'
        },
        {
            header: '강좌 ID',
            name: 'lectId',
            hidden: true
        },
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
                    return '종료'; // false인 경우 "마감됨"으로 표시
                } else {
                    return ''; // null 또는 다른 값인 경우 공백으로 표시
                }
            }
        },
    ],
});
console.log(grid)
//  그리드 요소에 CSS 스타일 적용
grid.el.style.width = '1500px';
grid.el.style.marginLeft = '90px';

// // 버튼 클릭시 데이터 조회
window.addEventListener('load', async () => {
    try {
        const response = await fetch('/admin/ttr/search');
        const data2 = await response.json();
        console.log(data2);
        grid.resetData(data2);
        resultElement.textContent = `검색결과 : ${data2.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

// 'name' 열의 모든 셀을 선택
const nameCells = document.querySelectorAll('.tui-grid-cell-name');

// 선택한 모든 셀에 대해 텍스트를 가운데 정렬
nameCells.forEach(cell => {
    cell.style.textAlign = 'center';
});

/*----------------하단 첫번째 테이블-----------------*/

 const grid2 = new tui.Grid({
        el: document.getElementById('grid2'),
        "result": true,
        data: [{
            "contents": [
                {
                    header: '차시순서',
                    name: 'nthSequence'
                },
                {
                    header: '차시명',
                    name: 'nthName'
                },
                {
                    header: '차시 학습시간',
                    name: 'contsTime'
                },
                {
                    header: '차시ID',
                    name: 'contsNo',
                    hidden: true
                },
                {
                    header: '차시관리번호',
                    name: 'nthId',
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
        rowHeaders: ['checkbox'],
        pageOptions: {
            useClient: true,
            perPage: 5,
        },
        "columns": [
            {
                header: '차시순서',
                name: 'nthSequence'
            },
            {
                header: '차시명',
                name: 'nthName'
            },
            {
                header: '차시 학습시간',
                name: 'contsTime'
            },
            {
                header: '차시ID',
                name: 'contsNo',
                hidden: true
            },
            {
                header: '차시관리번호',
                name: 'nthId',
                hidden: true
            }
        ],
    });


// grid2 테이블 업데이트 함수


//  그리드 요소에 CSS 스타일 적용
grid2.el.style.width = '700px';
grid2.el.style.marginLeft = '0px';


$(document).ready(function() {
    // 첫 번째 그리드 초기화
    const container1 = document.getElementById("grid");
    const options1 = {
        // 그리드 옵션 설정 (선택적)
    };
    const grid1 = new tui.Grid(container1, options1);

    // 두 번째 그리드 초기화
    const container2 = document.getElementById("grid2");
    const options2 = {
        // 그리드 옵션 설정 (선택적)
    };
    const grid2 = new tui.Grid(container2, options2);

    // 조회 버튼 클릭 이벤트 처리
    $("#search-button").click(function() {
        // 서버로 데이터 요청
        $.ajax({
            type: "GET",
            url: "/admin/ttr/api", // Spring Boot Controller의 엔드포인트 경로
            dataType: "json",
            success: function(data) {
                // 데이터를 첫 번째 그리드에 표시
                grid1.resetData(data);
            },
            error: function() {
                // 오류 처리
                alert("데이터를 불러오는 중에 오류가 발생했습니다.");
            }
        });
    });

    // 검색 버튼 클릭 이벤트 처리
    $("#search-input").on('input', function() {
        const keyword = $(this).val();
        // 서버로 검색 데이터 요청
        $.ajax({
            type: "GET",
            url: "/admin/ttr/search", // 검색을 처리하는 엔드포인트 경로
            data: { keyword: keyword },
            dataType: "json",
            success: function(data) {
                // 검색 결과를 첫 번째 그리드에 표시
               const formControl1 = document.querySelector('lectId')
            },
            error: function() {
                // 오류 처리
                alert("데이터 검색 중에 오류가 발생했습니다.");
            }
        });
    });
});

$(document).ready(function() {
    let selectedValue = "(전체)";

    $("#dropdown-menu").on("change", function() {
        selectedValue = $(this).val(); // Get the selected value

        updateData(selectedValue);
    });

    function updateData(selectedValue) {

        console.log(selectedValue);
    }
});


    function updateData(selectedValue) {
    // 선택된 값(selectedValue)을 활용하여 작업을 수행
    if (selectedValue === "(전체)") {
        // "(전체)"를 선택한 경우 모든 데이터를 표시하거나 관련 작업 수행
        // 예: 서버로 데이터 요청
        console.log("(전체) 선택됨");
    } else if (selectedValue === "운영중") {
        // "운영중"을 선택한 경우 해당 데이터만 표시하거나 관련 작업 수행
        // 예: 서버로 데이터 요청 또는 데이터 필터링
        console.log("운영중 선택됨");
    } else if (selectedValue === "종료") {
        // "종료"를 선택한 경우 해당 데이터만 표시하거나 관련 작업 수행
        // 예: 서버로 데이터 요청 또는 데이터 필터링
        console.log("종료 선택됨");
    }
    // 다른 드롭다운 항목에 대한 조건을 추가할 수 있습니다.
}



/* 체크박스 선택란*/
const itemAll = document.querySelector('#item-all');
const itemRunning = document.querySelector('#item-running');
const itemClosed = document.querySelector('#item-closed');

itemAll.addEventListener('change', function() {
    // "(전체)" 체크박스 항목 상태 변경
    // 원하는 동작을 수행
    if (itemAll.checked) {
        console.log("(전체) 체크됨");
    } else {
        console.log("(전체) 체크 해제됨");
    }
});

itemRunning.addEventListener('change', function() {
    // "운영중" 체크박스 항목 상태 변경
    // 원하는 동작을 수행
    if (itemRunning.checked) {
        console.log("운영중 체크됨");
    } else {
        console.log("운영중 체크 해제됨");
    }
});

itemClosed.addEventListener('change', function() {
    // "종료" 체크박스 항목 상태 변경
    // 원하는 동작을 수행
    if (itemClosed.checked) {
        console.log("종료 체크됨");
    } else {
        console.log("종료 체크 해제됨");
    }
});
/* 여기까지 */
const searchBtn = document.querySelector('#search-button');
const searchInput = document.querySelector('#search-input');
const resultElement = document.querySelector('.result2');
const searchSelect = document.querySelector('.box');

/* 온라인강좌명 검색 */
searchBtn.addEventListener('click', async () => {
    try {
        const searchInputValue = searchInput.value;
        const searchSelectValue = searchSelect.value;

        let isActiveValue = searchSelectValue;

        if (searchSelectValue === "운영중") {
            isActiveValue = 1;
        } else if (searchSelectValue === "종료") {
            isActiveValue = 0;
        }
        console.log(isActiveValue);

        let apiUrl;
        console.log("searchInputValue : " + searchInputValue)
        console.log("searchSelectValue : " + searchSelectValue)

        if(searchInputValue && (isActiveValue !== undefined && isActiveValue !== "")) {
            apiUrl = `/admin/api/lectName/search?lectName=${searchInputValue}&isActive=${isActiveValue}`
        }else if (searchInputValue) {
            // 게시판 종류 검색어만 입력한 경우
            console.log("searchSelectValue : " + searchSelectValue)
            apiUrl = `/admin/api/ttr/lectName/search?lectName=${searchInputValue}`;
        }else if (isActiveValue !== undefined) {
            apiUrl = `/admin/api/ttr/isActive/search?isActive=${isActiveValue}`;
        }

        const response = await fetch(apiUrl);
        const searchData = await response.json();
        console.log("search : " + JSON.stringify(searchData));

        grid.resetData(searchData); // 검색 결과를 그리드에 업데이트
        resultElement.textContent = `검색결과 : ${searchData.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

/* 강의 차시정보 하단테이블 비동기 처리*/

grid.on('click', async (ev) => {
    const rowKey = ev.rowKey;
    const rowData = grid.getRow(rowKey);
    const lectId = rowData.lectId;
    console.log("grid1LectId : " + lectId);

    try {
        // memberId를 사용하여 해당 학생의 데이터를 가져오는 API 호출
        const response = await fetch(`/admin/api/nthName/search?lectId=${lectId}`);
        const studentData = await response.json();

        // grid2에 데이터 설정
        grid2.resetData(studentData);
    } catch (error) {
        console.error('An error occurred:', error);
    }
});

/* 강의 차시정보 신규/저장/삭제 */
grid2.on('click', async (ev) => {
    const rowKey = ev.rowKey;
    const rowData = grid2.getRow(rowKey);
    const contsNo = rowData.contsNo;
    const nthId = rowData.nthId;
    const nthSequence = rowData.nthSequence;
    const contsName = rowData.contsName;
    const contsTime = rowData.contsTime;
    const contsYout = rowData.contsYout;
    const contsDetail = rowData.contsDetail;
    console.log("contsNo : " + contsNo)
    console.log("nthId : " + nthId)

    // 데이터를 가져오는 API 호출


        // 테이블(`tb1`)의 각 입력 필드에 값을 설정
        document.getElementById('contsNthIdInput').value = nthId;
        document.getElementById('contsNoInput').value = contsNo;
        document.getElementById('contsSequenceInput').value = nthSequence;
        document.getElementById('contsNameInput').value = contsName;
        document.getElementById('contsTimeInput').value = contsTime;
        document.getElementById('contsYoutInput').value = contsYout;
        document.getElementById('contsDetailInput').value = contsDetail;
        // console.log("nthId : " + lectureData.nthId)
        // console.log("nthSequence : " + lectureData.nthSequence)
});


/* 신규 버튼을 눌렀을 때 강의 차시정보 생성*/
const createButton = document.querySelector('#createBtn');
createButton.addEventListener('click', () => {
    // 새로운 빈 행 생성
    const newEmptyRow = {
        nthId: '',
        contsNo: '',
        nthSequence: '',
        contsName: '',
        contsTime: '',
        contsYout: '',
        contsDetail: ''
    };
    grid2.appendRow(newEmptyRow);
});