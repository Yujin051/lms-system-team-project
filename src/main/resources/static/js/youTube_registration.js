const gridData = [
    {
        name: '',
        artist: '',
        release: '',
        type: '',
        genre: ''
    },
    {
        name: '',
        artist: '',
        release: '',
        type: '',
        genre: ''
    },
    {
        name: '',
        artist: '',
        release: '',
        type: '',
        genre: ''
    },
    {
        name: '',
        artist: '',
        release: '',
        type: '',
        genre: ''
    },
    {
        name: '',
        artist: '',
        release: '',
        type: '',
        genre: ''
    },
    {
        name: '',
        artist: '',
        release: '',
        type: '',
        genre: ''
    }
];

const grid = new tui.Grid({
    el: document.getElementById('grid'),
    data: gridData,
    scrollX: false,
    scrollY: false,
    rowHeaders: ['checkbox'],
    rowHeight: 84.8,
    columns: [
        {
            header: '콘텐츠명',
            name: 'name'
        },
        {
            header: 'YouTube 연동명',
            name: 'youtubeLink'
        },
        {
            header: '콘텐츠명',
            name: 'artist'
        },
        {
            header: '교과목',
            name: 'type'
        },
        {
            header: '학습시간',
            name: 'release'
        }
    ]
});


//  그리드 요소에 CSS 스타일 적용
grid.el.style.width = '700px';


// API 인증 버튼
const auth = document.querySelector("#api-auth")
auth.addEventListener('click', () => {
    location.href='/youtubeAPIAuth'
})

// 신규 버튼 -> 모달창
// 모달창 외부 클릭하면 닫기
const upload = document.querySelector("#upload")
const mWrap = document.querySelector(".modalWrap")
const modal = document.querySelector(".modal")


upload.addEventListener('click', () => {
    const isModalVisible = modal.style.display === 'none'
    if(isModalVisible) {
        modal.style.display = 'block'
        mWrap.style.display = 'block'
    }
})
mWrap.addEventListener('click', (e) => {
    if(e.target === mWrap){
        mWrap.style.display = 'none'
        modal.style.display = 'none'
    }
})

// ajax로 비디오 업로드 처리
const uploadBtn = document.querySelector("#upClose")
const vidUpload = document.querySelector("#videoUpload")
const url = "/youtubeUpload"
uploadBtn.addEventListener('click', () => {
    let formData = new FormData(vidUpload)
    console.log(formData)

    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        processData: false, // multiPartFile 전송할 때 사용하는 ajax 옵션
        contentType: false,
        success: function(response) {
            alert(response.toString())
            // 모달 창 닫기
            const isModalVisible = modal.style.display !== 'none'
            if(isModalVisible) {
                mWrap.style.display = 'none'
                modal.style.display = 'none'
            }

            // 성공 후 폼 내부 데이터 초기화
            vidUpload.reset()
        },
        error: function() {
            alert("업로드 에러")
            // 모달 창 닫기
            const isModalVisible = modal.style.display !== 'none'
            if(isModalVisible) {
                mWrap.style.display = 'none'
                modal.style.display = 'none'
            }

            // 폼 초기화
            vidUpload.reset()
        }
    })
})

