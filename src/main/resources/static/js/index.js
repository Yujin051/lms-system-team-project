const userManagement = document.querySelector(".user_management_tap");
const studentManagement = document.querySelector(".student_management");
const teacherManagement = document.querySelector(".teacher_management");

userManagement.addEventListener("click", function () {
    const isStudentVisible = studentManagement.style.display !== "none";
    const isTeacherVisible = teacherManagement.style.display !== "none";

    studentManagement.style.display = isStudentVisible ? "none" : "block";
    teacherManagement.style.display = isTeacherVisible ? "none" : "block";
});

const classManagement = document.querySelector(".class_management_tap");
const courseManagement = document.querySelector(".course_management");
const courseRegistrationManagement = document.querySelector(".course_registration_management");
const onlineCourseStatus = document.querySelector(".online_course_status");

classManagement.addEventListener("click", function () {
    const isCourseManagement = courseManagement.style.display !== "none";
    const isCourseRegistrationManagement = courseRegistrationManagement.style.display !== "none";
    const isOnlineCourseStatus = onlineCourseStatus.style.display !== "none";

    courseManagement.style.display = isCourseManagement ? "none" : "block";
    courseRegistrationManagement.style.display = isCourseRegistrationManagement ? "none" : "block";
    onlineCourseStatus.style.display = isOnlineCourseStatus ? "none" : "block";
});

const courseManagementTap = document.querySelector(".course_management_tap");
const onlineContentLectures = document.querySelector(".online_content_lectures");
const onlineLectureInfoManagement = document.querySelector(".online_lecture_info_management");
const onlineLectureAttendanceStatus = document.querySelector(".online_lecture_attendance_status");

courseManagementTap.addEventListener("click", function () {
    const isOnlineContentVisible = onlineContentLectures.style.display !== "none";
    const isOnlineInfoVisible = onlineLectureInfoManagement.style.display !== "none";
    const isAttendanceVisible = onlineLectureAttendanceStatus.style.display !== "none";

    onlineContentLectures.style.display = isOnlineContentVisible ? "none" : "block";
    onlineLectureInfoManagement.style.display = isOnlineInfoVisible ? "none" : "block";
    onlineLectureAttendanceStatus.style.display = isAttendanceVisible ? "none" : "block";
});

const gradeManagement = document.querySelector(".grade_management");
const overallGradeManagement = document.querySelector(".overall_grade_management");

gradeManagement.addEventListener("click", function () {
    const isOverallGradeVisible = overallGradeManagement.style.display !== "none";

    overallGradeManagement.style.display = isOverallGradeVisible ? "none" : "block";
});

const portalManagement = document.querySelector(".portal_management");
const writePost = document.querySelector(".write_post");
const postInfo = document.querySelector(".post_info");

portalManagement.addEventListener("click", function () {
    const isWritePost = writePost.style.display !== "none";
    const isPostInfo = postInfo.style.display !== "none";

    writePost.style.display = isWritePost ? "none" : "block";
    postInfo.style.display = isPostInfo ? "none" : "block";
});