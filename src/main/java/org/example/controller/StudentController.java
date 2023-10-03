package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/grade")
    public String studentGrade() {
        return "/student/gradeCheck";
    }

    @GetMapping("/lecture")
    public String studentLecture() {
        return "/student/myLecture";
    }

    @GetMapping("/lecturePlan")
    public String studentPlan() {
        return "/student/lecturePlan";
    }
}
