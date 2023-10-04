package org.example.controller.managerController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class GradeManagementController {

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
