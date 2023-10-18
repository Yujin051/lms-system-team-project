package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.EnteringGradeDto;
import org.example.entity.Member;
import org.example.service.MemberService;
import org.example.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/prof")
@RequiredArgsConstructor
public class ProfessorController {


    private final MemberService memberService;
    private final ProfessorService professorService;


    // 강사 메인페이지
    @GetMapping("")
    public String profMain() {
        return "/prof/prof_main";
    }

    // 강사 나의 강의실
    @GetMapping("/lecture")
    public String profLecture() {
        return "/prof/myLecture";
    }

    // 강사 강의계획서
    @GetMapping("/lectureplan")
    public String profPlan() {
        return "/prof/lecturePlan";
    }

    // 강사 마이페이지
    @GetMapping("/mypage")
    public String profMyPage() {
        return "/prof/prof_my_page";
    }

    // 강사 강의이력
    @GetMapping("/history")
    public String lectureHistory() {

        return "/prof/prof_class";
    }


    /**
     * 강사 : 나의강의실 - 출결조회
     * @author 임휘재
     */
    @GetMapping("/att")
    public String AttendanceCheck(){
        return "/prof/attendanceCheck";
    }

    /**
     * 강사 : 나의강의실 - 과제출제
     * @author 임휘재
     */
    @GetMapping("/assi")
    public String assignment() {
        return "/prof/assignment";
    }

    /**
     * 강사 : 나의강의실 - 과제제출정보
     * @author 임휘재
     */
    @GetMapping("/assiInfo")
    public String assiSmInfo(){
        return "/prof/assiSmInfo";
    }

    //강사 : 나의강의실 - 과제정보쓰기
    @GetMapping("/assiWrite")
    public String assiWrite(){
        return "/prof/assiWrite";
    }

    //강사 : 나의강의실 - 성적입력
    @GetMapping("/assiGrade")
    public String assiGrade(Model model, Principal principal){
        // 사용자 로그인 아이디 가져오기
        String loginId = principal.getName();
        // 사용자 정보 가져오기(member)
        Member member = memberService.memberView(loginId);

        model.addAttribute("list", professorService.AssiGradeLectInfoCheckList(member.getId()));
        return "/prof/assiGrade";
    }

    // 강사 : 성적입력 작성하기
    @RequestMapping(value = "/assiGrade/write", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> findEnteringGrade(@RequestParam String lectId) {
        log.info("lect아이디는?", lectId);
        Long LongLectId = Long.valueOf(lectId);
        List<EnteringGradeDto> enteringGradeDtoList = professorService.EnteringGradeCheckList(LongLectId);
        log.info("디티오는?, {}", enteringGradeDtoList.toString());
        return ResponseEntity.status(HttpStatus.OK).body(enteringGradeDtoList);
    }

    @GetMapping("/onlineclass")
    public String test2() {
        return "/prof/onlineclass";
    }
}
