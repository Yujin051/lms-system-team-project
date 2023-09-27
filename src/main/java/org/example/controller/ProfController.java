package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "prof")
public class ProfController {
    @GetMapping(value="")
    public String profRoot() {
        return "prof/root";
    }
}
