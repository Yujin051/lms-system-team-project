package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping(value= "/admin/login")
    public String adminLogin() {
        return "/admin/login";
    }

}
