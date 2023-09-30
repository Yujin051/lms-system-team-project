package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/manager/index")
    public String managerHome(){
        return "admin_default";
    }

    @GetMapping("/test")
    public String test() {
        return "admin_lecture_manage";
    }
}
