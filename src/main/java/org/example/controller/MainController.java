package org.example.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping(value="/")
    public String main(Principal principal) {
        System.out.println(principal.getName());

        return "main";
    }
}
