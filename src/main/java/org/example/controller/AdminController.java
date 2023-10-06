package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AttendanceStatusDto;
import org.example.dto.LectNthDto;
import org.example.entity.LectNth;
import org.example.service.AdminAttendanceStatusService;
import org.example.service.LectNthService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j

public class AdminController {

    private final LectNthService lectNthService;


    // 어드민 메인페이지
    @GetMapping("")
    public String adminMain() {
        return "/admin/main";
    }

    // 나중에 비동기로 전환할 수 있으면 전환할 것
    @GetMapping("/lecturemanage")
    public String adminLecture() {
        return "/admin/admin_lecture_manage";
    }

    @GetMapping("/lectureapply")
    public String adminApply() {
        return "/admin/admin_lecture_apply";
    }

    @GetMapping("/studentmanage")
    public String adminStudent() {
        return "/admin/admin_student_manage";
    }

    @GetMapping("/profmanage")
    public String adminProf() {
        return "/admin/admin_prof_manage";
    }


    /**
     * 관리자 : 전체관리성적
     * @author 임휘재
     */
    @GetMapping("/grade")
    public String grade(){
        return "/admin/gradeManagement";
    }

    /**
     * 관리자 : 게시글 관리(담당용)
     * @author 임휘재
     */
    @GetMapping("/postWrite")
    public String postWrite(){
        return "/admin/postWrite";
    }

    /**
     * 관리자 : 게시글 정보관리
     * @author 임휘재
     */
    @GetMapping("/postInfo")
    public String postInfo(){
        return "/admin/postInfo";
    }


    //온라인강의수강현황
    @GetMapping("/as")
    public String attendanceStatus() {
//        List<AttendanceStatusDto>  asList = adminAttendanceStatusServic.attendanceStatusList();/**/
        return "/admin/attendance_status";
    }

    //온라인강의정보관리
    @GetMapping("/ttr")
    public String thisTime() {
        List<LectNthDto> nthList = lectNthService.lectNthList();
        return "/admin/thisTime_registration";
    }



    // 검색기능
  @GetMapping("/ttr/search")
  public String search(String keyword, Model model) {
    List<LectNth> searchList = lectNthService.search(keyword);

    model.addAttribute("searchList", searchList);

    return "ttr-search";
    }




    //온라인강의콘텐츠관리
    @GetMapping("/ytr")
    public String youTubeRegistration() {

        return "/admin/youTube_registration";
    }
}
