package org.example.controller;

import org.example.dto.LectInfoDTO;
import org.example.entity.LectInfo;
import org.example.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private LectureService courseService;

    @GetMapping("/test")
    public String test1() {
        return "/test";
    }

    @RequestMapping(value = "/courses/find", method = RequestMethod.GET)
    @ResponseBody
    public List<LectInfoDTO> findCoursesByMemberAndSemester(@RequestParam Long memberId, @RequestParam String year, @RequestParam String semester) {
        List<LectInfo> lectInfoList = courseService.findCoursesByMemberAndSemester(memberId, year, semester);

        List<LectInfoDTO> lectInfoDTOList = new ArrayList<>();
        for (LectInfo lectInfo : lectInfoList) {
            lectInfoDTOList.add(LectInfoDTO.fromLectInfo(lectInfo));
        }
        return lectInfoDTOList;
    }

    @GetMapping("/test2")
    public String test2() {
        return "/student/lecturemain";
    }

    @GetMapping("/test3")
    public String test3() { return "/student/assiView";}

    @GetMapping("/test4")
    public String test4() { return "/student/my_classroom";}
}