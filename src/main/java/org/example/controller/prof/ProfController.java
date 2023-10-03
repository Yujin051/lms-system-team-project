package org.example.controller.prof;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prof")
public class ProfController {

    //강사 : 나의강의실 - 출결조회
    @GetMapping("/att")
    public String AttendanceCheck(){
        return "/prof/attendanceCheck";
    }

    //강사 : 나의강의실 - 과제출제
    @GetMapping("/assi")
    public String assignment() {
        return "/prof/assignment";
    }

    //강사 : 나의강의실 - 과제제출정보
    @GetMapping("/assiInfo")
    public String assiSmInfo(){
        return "/prof/assiSmInfo";
    }

    //강사 : 나의강의실 - 과제정보쓰기
    @GetMapping("/assiWrite")
    public String assiWrite(){
        return "/prof/assiWrite";
    }
}
