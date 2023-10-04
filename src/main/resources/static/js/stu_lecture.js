const lecCon = document.querySelector(".lectureName")
const lecDetail = document.querySelector(".lectureClick")
const plus = document.querySelector("#plus")
const minus = document.querySelector("#minus")

lecCon.addEventListener('click', function () {
    const isLectureVisible = lecDetail.style.display !== 'none'

    lecDetail.style.display = isLectureVisible ? 'none' : 'block'
    plus.style.display = isLectureVisible ? 'block' : 'none'
    minus.style.display = isLectureVisible ? 'none' : 'block'
})