package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    // 학생 메인 페이지
    @GetMapping("")
    public String stuMain() {
        return "/student/stud_main";
    }
    // 학생 성적 페이지
    @GetMapping("/grade")
    public String studentGrade() {
        return "/student/gradecheck";
    }

    // 학생 나의 강의현황
    @GetMapping("/lecture")
    public String studentLecture() {
        return "/student/mylecture";
    }

    // 학생 강의계획서 보기
    // 수정필요
    @GetMapping("/lectureplan")
    public String studentPlan() {
        return "/student/lecturePlan";
    }

    // 학생 마이페이지
    @GetMapping("/mypage")
    public String studentMyPage() {
        return "/student/stud_my_page";
    }

    @GetMapping("/testattendance")
    public String test1() {
        return "/student/attendance";
    }

    @GetMapping("/test")
    public String test2() {
        return "/student/onlineclass";
    }

    /**
     * 학생 : 과제출제
     * @author 임휘재
     */
    @GetMapping("/assi")
    public String assiView(){
        return "/student/assiView";
    }


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
