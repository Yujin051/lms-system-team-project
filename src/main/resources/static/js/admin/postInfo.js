const grid1 = new tui.Grid({
    el: document.getElementById('grid1'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '게시판 번호',
                name: 'boardId',
                sortingType: 'asc',
                sortable: true,
                width: 200
            },
            {
                header: '게시판 이름',
                name: 'boardName',
                sortingType: 'asc',
                sortable: true,
                width: 630
            },
            {
                header: '게시판 종류',
                sortingType: 'asc',
                name: 'boardType',
                sortable: true,
                width: 630
            }
        ]
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
            header: '게시판 번호',
            name: 'boardId',
            sortingType: 'asc',
            sortable: true,
            width: 200,
            align: 'center'
        },
        {
            header: '게시판 이름',
            name: 'boardName',
            sortingType: 'asc',
            sortable: true,
            width: 630
        },
        {
            header: '게시판 종류',
            sortingType: 'asc',
            name: 'boardType',
            sortable: true,
            width: 630
        }
    ]
});

const selectButton = document.querySelector('.post_info_select_btn');
const boardIdInput = document.querySelector('.post_info_board_id2');
const boardNameInput = document.querySelector('.post_info_board_name');
const boardTypeSelect = document.querySelector('.post_info_board_type');
const resultElement = document.querySelector('.result3');

// 게시판 정보 관리 : 게시판 정보 목록 조회
window.addEventListener('load', async () => {
    try {
        const response = await fetch('/admin/api/postInfo');
        const data2 = await response.json();
        console.log(data2);
        grid1.resetData(data2);
        resultElement.textContent = `검색결과 : ${data2.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

selectButton.addEventListener('click', async () => {
    try {
        const boardId = boardIdInput.value;
        const boardName = boardNameInput.value;
        const boardType = boardTypeSelect.value;

        let apiUrl;

        if (boardId && boardType === "") {
            // 게시판 번호 검색어만 입력한 경우
            apiUrl = `/admin/api/postInfo/boardId/search?boardId=${boardId}`;
        }
        if (boardName) {
            // 게시판 이름
            apiUrl = `/admin/api/postInfo/boardName/search?boardName=${boardName}`;
        }
        if (boardType) {
            // 게시판 종류
            apiUrl = `/admin/api/postInfo/boardType/search?boardType=${boardType}`;
        }
        if (boardId && boardName) {
            // 게시판 번호, 게시판 이름
            apiUrl = `/admin/api/postInfo/boardIdAndBoardName/search?boardId=${boardId}&boardName=${boardName}`;
        }
        if (boardId && boardType) {
            // 게시판 번호, 게시판 종류
            apiUrl = `/admin/api/postInfo/boardIdAndBoardType/search?boardId=${boardId}&boardType=${boardType}`;
        }
        if (boardId && boardType && boardName) {
            apiUrl = `/admin/api/postInfo/all/search?boardId=${boardId}&boardType=${boardType}&boardName=${boardName}`;
        }
        if (!boardId && !boardType && !boardName) {
            apiUrl = '/admin/api/postInfo'
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

// 신규 버튼 눌렀을 때 그리드 내의 행 추가
const createButton = document.querySelector('.post_info_create_btn');
createButton.addEventListener('click', () => {
    // 새로운 빈 행 생성
    const newEmptyRow = {
        boardId: '',
        boardType: '',
        boardName: ''
    };
    grid1.appendRow(newEmptyRow);
});

grid1.on('click', async (ev) => {
    const rowKey = ev.rowKey;
    const rowData = grid1.getRow(rowKey);
    const boardId = rowData.boardId;
    const boardName = rowData.boardName;
    const boardType = rowData.boardType;
    console.log("boardId : " + boardId);
    // const boardId2 = typeof boardId === 'number' ? boardId : 0;
    // console.log("boardId2 : " + boardId2);

    const boardIdInput = document.getElementById('boardId');
    const boardTypeInput = document.getElementById('boardType');
    const boardNameInput = document.getElementById('boardName2');
    if (boardIdInput) {
        boardIdInput.value = boardId;
    }
    if(boardNameInput) {
        boardNameInput.value = boardName;
        console.log("boardName : " + boardName);
    }
    if(boardTypeInput) {
        boardTypeInput.value = boardType;
    }
});


const saveButton = document.querySelector('.post_info_save_btn');
saveButton.addEventListener('click', async () => {

    const boardIdInput = document.getElementById('boardId').value;
    const boardTypeInput = document.getElementById('boardType').value;
    const boardNameInput = document.getElementById('boardName2').value;

    const data = {
        boardId: boardIdInput,
        boardName: boardNameInput,
        boardType: boardTypeInput
    };
    console.log("boardId : " + data.boardId);
    console.log("boardName : " + data.boardName);

    try {
        const response = await fetch('/admin/api/postInfo/save', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('게시물이 성공적으로 저장되었습니다.');
        } else {
            alert('게시물 저장에 실패했습니다.');
        }
    } catch (error) {
        console.error('저장 중 오류 발생:', error);
    }
});

const deleteButton = document.querySelector('.post_info_delete_btn');
deleteButton.addEventListener('click', async () => {
    // 체크박스를 누른 행의 데이터 가져오기
    const checkedRows = grid1.getCheckedRows();
    console.log("check : " + checkedRows);

    if (checkedRows.length === 0) {
        alert('삭제할 행을 선택해주세요.');
        return;
    }

    const confirmDelete = confirm('선택한 행을 삭제하시겠습니까?');

    if (confirmDelete) {
        // 선택한 행을 삭제하기 위한 로직 추가
        for (const row of checkedRows) {
            const boardId = row.boardId;
            const boardName = row.boardName;

            try {
                const response = await fetch(`/admin/api/postInfo/delete?boardId=${boardId}`, {
                    method: 'DELETE',
                });

                if (response.ok) {
                    alert(`${boardId}번이 삭제되었습니다.`);
                    // 선택한 행을 그리드에서 제거
                    grid1.removeRow(row._attributes.rowKey);
                } else {
                    alert(`${boardId}번이 삭제되지 않았습니다.`);
                }
            } catch (error) {
                console.error(`게시물 이름 : ${boardName} 삭제 중 오류 발생:`, error);
            }
        }
    }
});

