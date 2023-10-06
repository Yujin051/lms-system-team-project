package org.example.controller;

import org.example.repository.LectInfoRepository;
import org.example.service.MemberService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/student")
public class StudentController {

//    private LectInfoRepository lectInfoRepository;

    @Autowired
    private StudentService studentService;
    // 학생 메인 페이지
    @GetMapping("")
    public String stuMain() {
        return "/student/stud_main";
    }
    // 학생 성적 페이지
    @GetMapping("/grade")
    public String studentGrade(Model model) {
        model.addAttribute("list", studentService.gradeCheckList());
        return "/student/gradecheck";
    }

    // 학생 나의 강의실
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

    @GetMapping("/onlineclass")
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
    public String courseRegisteration(Model model) {
        model.addAttribute("list", studentService.lectInfoList());
        return "/student/course_registeration";
    }


    /* 학생 나의 강의실 */
    @GetMapping("/mc")
    public String myClassroom() {
        return "/student/my_classroom";
    }
}
