package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value= "/login")
    public String adminLogin() {
        return "/admin/login";
    }

    // 나중에 비동기로 전환할 수 있으면 전환할 것
    @GetMapping("/lectureManager")
    public String adminLecture() {
        return "/admin/admin_lecture_manage";
    }

    @GetMapping("/lectureApply")
    public String adminApply() {
        return "/admin/admin_lecture_apply";
    }
}
