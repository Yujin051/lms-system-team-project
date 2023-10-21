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
            document.querySelector(".course_registration_management")
        ],
        text: document.querySelector(".class_management_text"),
        slideInClass: "slide-in"
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
            if (contentElement.style.display === "none") {
                contentElement.classList.add("slide-in");
                contentElement.style.display = "block";
            } else {
                contentElement.classList.add("slide-out");
                contentElement.style.display = "none";

                contentElement.addEventListener("animationend", function () {
                    contentElement.classList.remove("slide-out");
                });
            }
        });
        tabInfo.text.classList.toggle("before_click_color");
        tabInfo.text.classList.toggle("after_click_color");
    });
});