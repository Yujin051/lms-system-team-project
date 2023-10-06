package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.dto.ProfessorDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
import org.example.service.MemberService;
import org.example.service.ProfessorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/prof")
@AllArgsConstructor
@Log4j2
public class ProfessorController {

    private final MemberRepository memberRepository;
    private final ProfessorRepository professorRepository;
    private final ProfessorService professorService;
    private final MemberService memberService;

    // 강사 메인페이지
    @GetMapping("")
    public String profMain() {
        return "/prof/prof_main";
    }

    // 강사 나의 강의목록
    @GetMapping("/lecture")
    public String profLecture() {
        return "/prof/myLecture";
    }

    // 강사 강의계획서
    @GetMapping("/lecture번호/lectureplan")
    public String profPlan() {
        return "/prof/lecturePlan";
    }

    // 강사 마이페이지
    @GetMapping("/mypage")
    public String profMyPage(Model model, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Member member = memberRepository.findByUserId(userDetails.getUsername());

        model.addAttribute("professor", professorService.ProfessorView(member.getUserId()));
        model.addAttribute("member", member);

        return "/prof/prof_my_page";
    }

    @PostMapping("/mypage/modify")
    public String profUpdate(Professor professor, Member member, Long memberId, Principal principal, Model model, Authentication auth) {

        Member memberT = memberService.memberView(principal.getName());
        memberT.setUserName(member.getUserName());
        memberT.setUserBirthday(member.getUserBirthday());
        memberT.setUserPhoneNum(member.getUserPhoneNum());
        memberT.setUserEmail(member.getUserEmail());
        memberT.setUserAddr(member.getUserAddr());

        // html에서 th:each때문에 못들어가는것 같은 예감이 조금 드는데 모르겠다
        Professor professorT = professorService.ProfessorView(memberT.getId());
        professorT.setProfBank(professor.getProfBank());
        professorT.setProfWork(professor.getProfWork());
        professorT.setProfAccount(professor.getProfAccount());
        professorT.setProfAgency(professor.getProfAgency());
        professorT.setActive(professor.isActive());

        log.info("멤버아이디는 "+memberId);
        professorService.professorUpdate(professorT);
        model.addAttribute("message", "정보가 수정되었습니다.");
        model.addAttribute("SearchUrl", "/prof");
        return "/student/message";
    }

//  실험중인것
//    Professor professorT = new Professor();
//    professorT.setProfBank(professor.getProfBank());
//    professorT.setProfWork(professor.getProfWork());
//    professorT.setProfAccount(professor.getProfAccount());
//    professorT.setProfAgency(professor.getProfAgency());
//    professorT.setActive(professor.isActive());
//
//    // memberT의 ID를 사용하여 professorT를 조회
//    Professor existingProfessor = professorService.ProfessorView(memberT.getId());
//    
//    if (existingProfessor != null) {
//        // existingProfessor가 존재할 경우, 데이터를 업데이트
//        existingProfessor.setProfBank(professorT.getProfBank());
//        existingProfessor.setProfWork(professorT.getProfWork());
//        existingProfessor.setProfAccount(professorT.getProfAccount());
//        existingProfessor.setProfAgency(professorT.getProfAgency());
//        existingProfessor.setActive(professorT.isActive());
//
//        professorService.professorUpdate(existingProfessor);
//        model.addAttribute("message", "정보가 수정되었습니다.");
//    } else {
//        // existingProfessor가 존재하지 않을 경우, 에러 메시지를 추가
//        model.addAttribute("errorMessage", "강사 정보를 찾을 수 없습니다.");
//    }
    
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
    public String assiGrade(){
        return "/prof/assiGrade";
    }
}
