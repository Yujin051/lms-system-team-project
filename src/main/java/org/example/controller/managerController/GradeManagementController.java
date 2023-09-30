package org.example.controller.managerController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class GradeManagementController {

    @GetMapping("/grade")
    public String grade(){
        return "/manager/gradeManagement";
    }

    @GetMapping("/postWrite")
    public String postWrite(){
        return "/manager/postWrite";
    }

}
