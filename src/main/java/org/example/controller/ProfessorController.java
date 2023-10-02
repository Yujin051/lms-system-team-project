package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prof")
public class ProfessorController {

    @GetMapping("/lecture")
    public String profLecture() {
        return "/prof/myLecture";
    }

    @GetMapping("/lecturePlan")
    public String profPlan() {
        return "/prof/lecturePlan";
    }
}
