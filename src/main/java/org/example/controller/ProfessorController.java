package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prof")
public class ProfessorController {

    // 강사 메인페이지
    @GetMapping("")
    public String profMain() {
        return "/prof/prof_main";
    }

    // 강사 나의 강의목록
    @GetMapping("/lecture")
    public String profLecture() {
        return "/prof/myLecture";
    }

    // 강사 강의계획서
    @GetMapping("/lectureplan")
    public String profPlan() {
        return "/prof/lecturePlan";
    }

    // 강사 마이페이지
    @GetMapping("/mypage")
    public String profMyPage() {
        return "/prof/prof_my_page";
    }

    @GetMapping("/history")
    public String lectureHistory() {
        return "/prof/prof_class";
    }
}
