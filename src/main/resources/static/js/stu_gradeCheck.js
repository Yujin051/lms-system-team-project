// 상세 정보 테이블
let gradeDetail = document.getElementById('detail')

// 상세 정보 버튼 클릭 이벤트 추가
const detailBtn = document.getElementById('toDetail')

detailBtn.addEventListener('click', () => toggleDetail(gradeDetail));

function toggleDetail(gradeDetail) {
    // 버튼 클릭하면 성적 상세정보 테이블 출력
    gradeDetail.style.display = 'block';
}