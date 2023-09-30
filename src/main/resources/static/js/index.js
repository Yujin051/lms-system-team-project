const tabs = [
    {
        tab: document.querySelector(".user_management_tap"),
        content: [
            document.querySelector(".student_management"),
            document.querySelector(".teacher_management")
        ],
        text: document.querySelector(".user_management_text"),
    },
    {
        tab: document.querySelector(".class_management_tap"),
        content: [
            document.querySelector(".course_management"),
            document.querySelector(".course_registration_management"),
            document.querySelector(".online_course_status")
        ],
        text: document.querySelector(".class_management_text"),
    },
    {
        tab: document.querySelector(".course_management_tap"),
        content: [
            document.querySelector(".online_content_lectures"),
            document.querySelector(".online_lecture_info_management"),
            document.querySelector(".online_lecture_attendance_status")
        ],
        text: document.querySelector(".course_management_text"),
    },
    {
        tab: document.querySelector(".grade_management"),
        content: [document.querySelector(".overall_grade_management")],
        text: document.querySelector(".grade_management_text"),
    },
    {
        tab: document.querySelector(".portal_management"),
        content: [
            document.querySelector(".write_post"),
            document.querySelector(".post_info")
        ],
        text: document.querySelector(".portal_management_text"),
    },
];

tabs.forEach((tabInfo) => {
    tabInfo.tab.addEventListener("click", function () {
        tabInfo.content.forEach((contentElement) => {
            contentElement.style.display =
                contentElement.style.display === "none" ? "block" : "none";
        });
        tabInfo.text.classList.toggle("before_click_color");
        tabInfo.text.classList.toggle("after_click_color");
    });
});
