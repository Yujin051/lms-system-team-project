package org.example.controller.stud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stud")
public class StudentController {
    @GetMapping("/assi")
    public String assiView(){
        return "/stud/assiView";
    }
}