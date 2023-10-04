package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // 어드민 메인페이지
    @GetMapping("")
    public String adminMain() {
        return "/admin/main";
    }

    // 나중에 비동기로 전환할 수 있으면 전환할 것
    @GetMapping("/lecturemanager")
    public String adminLecture() {
        return "/admin/admin_lecture_manage";
    }

    @GetMapping("/lectureapply")
    public String adminApply() {
        return "/admin/admin_lecture_apply";
    }

    @GetMapping("/studentmanage")
    public String adminStudent() {
        return "/admin/admin_student_manage";
    }

    @GetMapping("/profmanage")
    public String adminProf() {
        return "/admin/admin_prof_manage";
    }


    /*
    전체관리성적
     */
    @GetMapping("/grade")
    public String grade(){
        return "/manager/gradeManagement";
    }

    /*
    게시글 관리(담당용)
     */
    @GetMapping("/postWrite")
    public String postWrite(){
        return "/manager/postWrite";
    }

    /*
    게시글 정보관리
     */
    @GetMapping("/postInfo")
    public String postInfo(){
        return "/manager/postInfo";
    }
}
