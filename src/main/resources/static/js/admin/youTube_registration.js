const dataSource = {
    api: {
        readData: {
            url: '/findAllConts', method: 'GET'
        }
    }
}

const grid = new tui.Grid({
    el: document.getElementById('grid'),
    data: dataSource,
    scrollX: false,
    scrollY: false,
    pageOptions: {
        useClient: true, // 클라이언트 페이징 활성화
        perPage: 6, // 페이지당 표시할 항목 수
    },
    rowHeaders: ['checkbox'],
    rowHeight: 84.8,
    columns: [
        {
            header: '콘텐츠명',
            name: 'contsName'
        },
        {
            header: 'YouTube 연동 코드',
            name: 'contsYout'
        },
        {
            header: '강좌명',
            name: 'lectName'
        },
        {
            header: '총 학습시간',
            name: 'contsTime'
        }
    ]
});

grid.on('click', (ev) => {
    const contsNo = grid.getRow(ev.rowKey).contsNo;
    const url = '/findContsDetail'

    let id = document.querySelector("#contsNo")
    let name = document.querySelector("#contsName")
    let detail = document.querySelector("#contsDetail")
    let yout = document.querySelector("#contsYout")
    let time = document.querySelector("#contsTime")

    // 서버에 요청하여 컨텐츠 정보 탐색
    $.ajax({
        url: url,
        method: 'GET',
        data: {
            contsNo: contsNo
        },
        success: function (response) {
            // 찾아온 데이터로 표 정렬
            id.value = response.contsNo
            name.value = response.contsName
            detail.value = response.contsDetail
            yout.value = response.contsYout
            time.value = response.contsTime
        },
        error: function () {
            alert("데이터 불러오기 오류")
        }
    })
})


//  그리드 요소에 CSS 스타일 적용
grid.el.style.width = '700px';


// API 인증 버튼
const auth = document.querySelector("#api-auth")
auth.addEventListener('click', () => {
    location.href = '/youtubeAPIAuth'
})

// 신규 버튼 -> 모달창
// 모달창 외부 클릭하면 닫기
const upload = document.querySelector("#upload")
const mWrap = document.querySelector(".modalWrap")
const modal = document.querySelector(".modal")


upload.addEventListener('click', () => {
    const isModalVisible = modal.style.display === 'none'
    if (isModalVisible) {
        modal.style.display = 'block'
        mWrap.style.display = 'block'
    }
})
mWrap.addEventListener('click', (e) => {
    if (e.target === mWrap) {
        mWrap.style.display = 'none'
        modal.style.display = 'none'
    }
})

// ajax로 비디오 업로드 처리
const uploadBtn = document.querySelector("#upClose")
const vidUpload = document.querySelector("#videoUpload")
const url = "/youtube/vidUpload"
uploadBtn.addEventListener('click', () => {
    let formData = new FormData(vidUpload)
    console.log(formData)

    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        processData: false, // multiPartFile 전송할 때 사용하는 ajax 옵션
        contentType: false,
        success: function (response) {
            // 모달 창 닫기
            const isModalVisible = modal.style.display !== 'none'
            if (isModalVisible) {
                mWrap.style.display = 'none'
                modal.style.display = 'none'
            }
            // 그리드 재갱신
            grid.resetData(response)

            // 성공 후 폼 내부 데이터 초기화
            vidUpload.reset()
        },
        error: function () {
            alert("업로드 에러")
            // 모달 창 닫기
            const isModalVisible = modal.style.display !== 'none'
            if (isModalVisible) {
                mWrap.style.display = 'none'
                modal.style.display = 'none'
            }

            // 폼 초기화
            vidUpload.reset()
        }
    })
})

// 업데이트(저장) 버튼
const save = document.querySelector("#save")
save.addEventListener('click', () => {

    const url = "/youtube/vidUpdate"

    let id = document.querySelector("#contsNo")
    let name = document.querySelector("#contsName")
    let detail = document.querySelector("#contsDetail")
    let yout = document.querySelector("#contsYout")

    $.ajax({
        url: url,
        type: 'POST',
        data: {
            contsNo: id.value,
            title: name.value,
            detail: detail.value,
            videoId: yout.value
        },
        success: function (response) {
            alert("성공적으로 업데이트되었습니다.")
            grid.resetData(response)
        },
        error: function () {
            alert("비디오 업데이트에 실패했습니다.")
        }
    })
})

// 시간 갱신 버튼
const dCheck = document.querySelector("#getDuration")

dCheck.addEventListener('click', () => {

    let yout = document.querySelector("#contsYout")
    let time = document.querySelector("#contsTime")
    const url = "/youtube/getDuration"

    $.ajax({
        url: url,
        type: 'POST',
        data: {
            videoId: yout.value
        },
        success: function (response) {
            // 갱신된 시간 값 넣어주기
            console.log(response.toString())
            // 그리드 리셋
            grid.resetData(response)
        },
        error: function () {
            alert = "갱신에 실패했습니다."
        }

    })
})

// 취소버튼 클릭 시 모달 창 닫음
const cancelButton = document.getElementById('upExit');
cancelButton.addEventListener('click', function() {
    mWrap.style.display = 'none';
    modal.style.display = 'none';
});