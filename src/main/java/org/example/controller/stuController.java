package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class stuController {

    /* 학생 과제출제 */
    @GetMapping("/scr")
    public String courseRegisteration() {
        return "/student/course_registeration";
    }


    /* 학생 나의 강의실 */
    @GetMapping("/mc")
    public String myClassroom() {
        return "/student/my_classroom";
    }
}
