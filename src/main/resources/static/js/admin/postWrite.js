const grid1 = new tui.Grid({
    el: document.getElementById('grid1'),
    "result": true,
    data: [{
        "contents": [
            {
                header: '작성자',
                name: 'userName',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '게시판 종류',
                name: 'boardType',
                sortingType: 'asc',
                sortable: true
            },
            {
                header: '게시글 번호',
                sortingType: 'asc',
                name: 'articleId',
                sortable: true
            },
            {
                header: '제목',
                sortingType: 'asc',
                name: 'articleTitle',
                sortable: true
            },
            {
                header: '작성일',
                sortingType: 'asc',
                name: 'articleAt',
                sortable: true
            },
            {
                header: '조회수',
                sortingType: 'asc',
                name: 'articleView',
                sortable: true
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
            header: '작성자',
            name: 'userName',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '게시판 종류',
            name: 'boardType',
            sortingType: 'asc',
            sortable: true
        },
        {
            header: '게시글 번호',
            sortingType: 'asc',
            name: 'articleId',
            sortable: true
        },
        {
            header: '제목',
            sortingType: 'asc',
            name: 'articleTitle',
            sortable: true
        },
        {
            header: '작성일',
            sortingType: 'asc',
            name: 'articleAt',
            sortable: true
        },
        {
            header: '조회수',
            sortingType: 'asc',
            name: 'articleView',
            sortable: true
        }
    ]
});

// 게시글 작성(담당용) 게시글 목록 조회
window.addEventListener('load', async () => {
    try {
        const response = await fetch('/admin/api/postWrite');
        const data2 = await response.json();
        console.log(data2);
        grid1.resetData(data2);
        resultElement.textContent = `검색결과 : ${data2.length} 건`;
    } catch (error) {
        console.error('데이터 가져오기 오류:', error);
    }
});

const retrieveButton = document.querySelector('.post_write_select_btn');
const searchBoardTypeInput = document.querySelector('.post_board_select');
const searchRequirementInput = document.querySelector('.post_search_requirement_select');
const postSearchRequirementInput = document.querySelector('.post_search_requirement_input');
const searchCalenderInput = document.querySelector('.post_reg_date');
const resultElement = document.querySelector('.result2');

retrieveButton.addEventListener('click', async () => {
    try {
        const boardType = searchBoardTypeInput.value;
        const searchTypeSelect = searchRequirementInput.value;
        const searchTypeInput = postSearchRequirementInput.value;
        const calenderGradeKeyword = searchCalenderInput.value;

        let apiUrl;

        if(!boardType && !searchTypeSelect && !searchTypeInput && !calenderGradeKeyword) {
            // 검색어가 비어있을때 조회버튼 누르면 전체조회
            apiUrl = `/admin/api/postWrite/search/no`;
        }
        if (boardType) {
            // 게시판 종류 검색어만 입력한 경우
            apiUrl = `/admin/api/postWrite/search/boardType?keyword=${boardType}`;
        }
        if (searchTypeSelect && searchTypeInput) {
            // 검색조건만 입력한 경우
            apiUrl = `/admin/api/postWrite/search/requirement?searchType=${searchTypeSelect}&keyword=${searchTypeInput}`;
        }else if (boardType && searchTypeSelect && searchTypeInput) {
            apiUrl = `/admin/api/postWrite/search/boardTypeAndArticleAt?boardTypeKeyword=${boardType}&searchType=${searchTypeSelect}&requirement=${searchTypeInput}`;
        }
        if(calenderGradeKeyword) {
            // 날짜만 입력한 경우
            apiUrl = `/admin/api/postWrite/search/articleAt?keyword=${calenderGradeKeyword}`;
        }
        if (boardType && searchTypeSelect && searchTypeInput && calenderGradeKeyword) {
            // 모든 검색 조건을 사용한 경우
            apiUrl = `/admin/api/postWrite/search/all?boardTypeKeyword=${boardType}&searchType=${searchTypeSelect}&requirement=${searchTypeInput}&articleAt=${calenderGradeKeyword}`;
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

// 신규버튼 누르면 새로운 행 추가
const createButton = document.querySelector('.post_write_create_btn');
createButton.addEventListener('click', () => {
    // 새로운 빈 행 생성
    const newEmptyRow = {
        userName: '관리자',
        boardType: '',
        articleId: '',
        articleTitle: '',
        articleAt: '',
        articleView: '',
    };

    grid1.appendRow(newEmptyRow);
});

grid1.on('click', async (ev) => {
    const rowKey = ev.rowKey;
    const rowData = grid1.getRow(rowKey);
    const articleId = rowData.articleId;
    console.log("articleId : " + articleId);
    const articleId2 = typeof articleId === 'number' ? articleId : 0;
    console.log("articleId2 : " + articleId2);

    if(articleId) {
        try {
            const response = await fetch(`/admin/api/postWrite/postContentInfo?articleId=${articleId}`);
            if (response.ok) {
                const postData = await response.json();

                // 가져온 데이터를 HTML 요소에 표시
                const postIdInput = document.getElementById('articleId');
                const postContInput = document.getElementById('postContTitle');
                const postWriterInput = document.getElementById('postWriter');
                const postRegDateInput = document.getElementById('postRegDate');
                const postViewInput = document.getElementById('postView');
                const boardTypeInput = document.getElementById('boardType');
                const isLockedCheckbox = document.getElementById('isLocked');
                const isDeletedCheckbox = document.getElementById('isDeleted');
                const realContent = document.querySelector('.real_content');
                console.log("postContInput : " + postContInput)

                if (postData.length > 0) {
                    const post = postData[0];
                    postIdInput.value = post.articleId;
                    postContInput.value = post.articleTitle;
                    postWriterInput.value = post.userName;
                    postRegDateInput.value = post.articleAt;
                    postViewInput.value = post.articleView;
                    boardTypeInput.value = post.boardType;
                    isLockedCheckbox.checked = post.isLocked;
                    isDeletedCheckbox.checked = post.isDeleted;

                    realContent.innerHTML = `<textarea>${post.articleContent}</textarea>`;
                } else {
                    // 데이터가 없을 경우 처리
                    console.log('데이터가 없습니다.');

                    // HTML 요소 초기화
                    postIdInput.value = '';
                    postContInput.value = '';
                    postWriterInput.value = '';
                    postRegDateInput.value = '';
                    postViewInput.value = '';
                    boardTypeInput.value = '';
                    isLockedCheckbox.checked = false;
                    isDeletedCheckbox.checked = false;
                    realContent.innerHTML = '<textarea></textarea>';
                }
            } else {
                // 응답이 실패한 경우 처리
                console.error('서버 응답 실패');
            }
        } catch (error) {
            console.error('데이터 가져오기 오류:', error);
        }
    } else {
        // HTML 요소 초기화
        const postIdInput = document.getElementById('articleId');
        const postContInput = document.getElementById('postContTitle');
        const postWriterInput = document.getElementById('postWriter');
        const postRegDateInput = document.getElementById('postRegDate');
        const postViewInput = document.getElementById('postView');
        const boardTypeInput = document.getElementById('boardType');
        const isLockedCheckbox = document.getElementById('isLocked');
        const isDeletedCheckbox = document.getElementById('isDeleted');
        const realContent = document.querySelector('.real_content');

        postIdInput.value = '';
        postContInput.value = '';
        postWriterInput.value = '관리자';
        postRegDateInput.value = '';
        postViewInput.value = '';
        boardTypeInput.value = '';
        isLockedCheckbox.checked = false;
        isDeletedCheckbox.checked = false;
        realContent.innerHTML = '<textarea></textarea>';
    }
});

const saveButton = document.querySelector('.post_write_save_btn');
saveButton.addEventListener('click', async () => {
    const postIdInput = document.getElementById('articleId').value;
    const postContInput = document.getElementById('postContTitle').value;
    const postWriterInput = document.getElementById('postWriter').value;
    const postRegDateInput = document.getElementById('postRegDate').value;
    const postViewInput = document.getElementById('postView').value;
    const boardTypeInput = document.getElementById('boardType').value;
    const isLockedCheckbox = document.getElementById('isLocked').checked;
    const isDeletedCheckbox = document.getElementById('isDeleted').checked;
    const realContent  = document.querySelector('.real_content textarea');

    const data = {
        articleId: postIdInput,
        articleTitle: postContInput,
        userName: postWriterInput,
        articleAt: postRegDateInput,
        articleView: postViewInput,
        boardType: boardTypeInput,
        isLocked: isLockedCheckbox,
        isDeleted: isDeletedCheckbox,
        articleContent: realContent.value,
    };
    console.log("articleTitle : " + data.articleTitle);
    console.log("articleContent : " + data.articleContent);
    console.log("articleId : " + data.articleId);

    try {
        const response = await fetch('/admin/api/postWrite/save', {
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

const deleteButton = document.querySelector('.post_write_delete_btn');
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
            const articleId = row.articleId;
            const userName = row.userName;

            try {
                const response = await fetch(`/admin/api/postWrite/delete?articleId=${articleId}`, {
                    method: 'DELETE',
                });

                if (response.ok) {
                    alert(`${userName}님이 쓴 글이 삭제되었습니다.`);
                    // 선택한 행을 그리드에서 제거
                    grid1.removeRow(row._attributes.rowKey);
                } else {
                    alert(`${userName}님이 쓴 글이 삭제 되지 않았습니다..`);
                }
            } catch (error) {
                console.error(`게시물 ${userName} 삭제 중 오류 발생:`, error);
            }
        }
    }
});















