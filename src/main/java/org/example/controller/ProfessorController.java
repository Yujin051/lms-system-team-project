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


    /**
     * 강사 : 나의강의실 - 출결조회
     * @author 임휘재
     */
    @GetMapping("/att")
    public String AttendanceCheck(){
        return "/prof/attendanceCheck";
    }

    /**
     * 강사 : 나의강의실 - 과제출제
     * @author 임휘재
     */
    @GetMapping("/assi")
    public String assignment() {
        return "/prof/assignment";
    }

    /**
     * 강사 : 나의강의실 - 과제제출정보
     * @author 임휘재
     */
    @GetMapping("/assiInfo")
    public String assiSmInfo(){
        return "/prof/assiSmInfo";
    }

    //강사 : 나의강의실 - 과제정보쓰기
    @GetMapping("/assiWrite")
    public String assiWrite(){
        return "/prof/assiWrite";
    }

    //강사 : 나의강의실 - 성적입력
    @GetMapping("/assiGrade")
    public String assiGrade(){
        return "/prof/assiGrade";
    }
}
