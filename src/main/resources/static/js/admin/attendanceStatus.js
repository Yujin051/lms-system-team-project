const grid1 = new tui.Grid({
    el: document.getElementById('grid1'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '개설강좌번호',
                name: 'lectId',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '강좌명',
                name: 'lectName',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '과목명',
                name: 'lectSubject',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '강의 시작 일시',
                name: 'lectStart',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '강의 종료 일시',
                name: 'lectEnd',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '강의 운영 상태',
                name: 'isActive',
                sortingType: 'asc',
                sortable: true
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
            header: '개설강좌번호',
            name: 'lectId',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강좌명',
            name: 'lectName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '과목명',
            name: 'lectSubject',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강의 시작 일시',
            name: 'lectStart',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강의 종료 일시',
            name: 'lectEnd',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '강의 운영 상태',
            name: 'isActive',
            sortingType: 'asc',
            sortable: true
        }
    ]
});

const selectButton = document.querySelector('.as_select_btn');
const resultElement = document.querySelector('.result4');
const lectNameInput = document.querySelector('.lectName');
const isActiveSelect = document.querySelector('#isActive');

// 학습강좌 조회
window.addEventListener('load', async () => {
    try {
        const response = await fetch('/admin/api/as');
        const data2 = await response.json();

        data2.forEach(item => {
            item.isActive = item.isActive ? '운영중' : '종료';
        });

        grid1.resetData(data2);
        resultElement.textContent = `검색결과 : ${data2.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

// 조회버튼 눌렀을때 학습강좌 조회
selectButton.addEventListener('click', async () => {
    try {
        const lectName = lectNameInput.value;
        const isActive = isActiveSelect.value;

        let apiUrl;

        if (lectName) {
            apiUrl = `/admin/api/as/lectName/search?lectName=${lectName}`;
        }
        if (isActive) {
            apiUrl = `/admin/api/as/isActive/search?isActive=${isActive}`;
        }
        if (lectName && isActive) {
            apiUrl = `/admin/api/as/all/search?lectName=${lectName}&isActive=${isActive}`;
        }
        if (!lectName && !isActive) {
            apiUrl = '/admin/api/as';
        }

        const response = await fetch(apiUrl);
        const searchData = await response.json();
        console.log("search : " + JSON.stringify(searchData));

        searchData.forEach(item => {
            item.isActive = item.isActive ? '운영중' : '종료';
        });

        grid1.resetData(searchData); // 검색 결과를 그리드에 업데이트
        resultElement.textContent = `검색결과 : ${searchData.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

//학생강좌 그리드의 특정 행을 눌렀을때 학생의 전체이수현황 정보가 나오게
grid1.on('click', async (ev) => {
    const rowData = grid1.getRow(ev.rowKey);
    const lectId = rowData.lectId;
    console.log("lectId : " + lectId);

    try {
        const response = await fetch(`/admin/api/as/status?lectId=${lectId}`);
        const studentDataList = await response.json();

        const tbody = document.querySelector('.as_tb tbody');
        tbody.innerHTML = ''; // 이전 데이터를 지우고 새로운 데이터 표시

        studentDataList.forEach(studentData => {
            const row = document.createElement('tr');

            // 각 학생 정보를 HTML에 표시
            const nameCell = document.createElement('td');
            nameCell.textContent = studentData.userName;
            row.appendChild(nameCell);

            const courseNameCell = document.createElement('td');
            courseNameCell.textContent = studentData.lectName;
            row.appendChild(courseNameCell);

            const creditCell = document.createElement('td');
            creditCell.textContent = studentData.lectCredit;
            row.appendChild(creditCell);

            const studentIdCell = document.createElement('td');
            studentIdCell.textContent = studentData.userId;
            row.appendChild(studentIdCell);

            const gradeCell = document.createElement('td');
            gradeCell.textContent = studentData.studGrade;
            row.appendChild(gradeCell);

            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});










