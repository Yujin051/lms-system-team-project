package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AdminController {

    @GetMapping(value= "/admin")
    public String adminroot() {
        //model.addAttribute("user",principal.getName());
        return "/admin/root";
    }

}
