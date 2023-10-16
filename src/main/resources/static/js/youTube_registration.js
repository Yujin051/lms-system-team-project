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

// 업로드 버튼 -> 모달창
const upload = document.querySelector("#upload")
upload.addEventListener('click', () => {
    const modal = document.querySelector(".modal")
    const modalWrap = document.querySelector(".modalWrap")
    const isModalVisible = modal.style.display !== 'none'

    modalWrap.style.display = isModalVisible ? 'none' : 'block'
    modal.style.display = isModalVisible ? 'none' : 'block'

})

