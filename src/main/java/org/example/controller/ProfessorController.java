package org.example.controller;

import jakarta.transaction.Transactional;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String profMain(Principal principal, Model model) {

        // 강사 프로필에 이름 띄우기
        Member member = memberRepository.findByUserId(principal.getName());
        String name = (member != null) ? member.getUserName() : "Unknown";
        String savedProfile = (member != null) ? member.getImgSaved() : "Unknown";
        model.addAttribute("name", name);
        model.addAttribute("profileImg", savedProfile);
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

        Professor professor = professorService.ProfessorView(member.getUserId());

        model.addAttribute("professor", professor);
        model.addAttribute("member", member);

        return "/prof/prof_my_page";
    }


    @PostMapping("/mypage/modify")
    public String profUpdate(Professor professor, Member member, Principal principal, Model model, @RequestPart MultipartFile file) throws Exception {
        Member memberT = memberService.memberView(principal.getName());
        log.info("1차 memberT : "+ memberT.getUserName());
        log.info("1차 memberT : "+ memberT.getUserId());
        memberT.setUserName(member.getUserName());
        memberT.setUserBirthday(member.getUserBirthday());
        memberT.setUserPhoneNum(member.getUserPhoneNum());
        memberT.setUserEmail(member.getUserEmail());
        memberT.setUserAddr(member.getUserAddr());

        memberService.updateMember(memberT, file);

        System.out.println("pm : "+professor);
        System.out.println("member"+ member);


        Professor professorT = professorService.ProfessorView(memberT.getId());

        professorT.setProfBank(professor.getProfBank());
        professorT.setProfWork(professor.getProfWork());
        professorT.setProfAccount(professor.getProfAccount());
        professorT.setProfAgency(professor.getProfAgency());

        professorService.professorUpdate(professorT);



        log.info("memberT : "+ memberT.getUserId() +"유저이름 : "+ memberT.getUserName());
        log.info("멤버아이디는 "+principal.getName());
        log.info("프로페서T"+professorT);

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

    @GetMapping("/test")
    public String proftest(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Member member = memberRepository.findByUserId(userDetails.getUsername());
        System.out.println(professorRepository.findByProfessorId(member.getId()));

        return "";
    }
}
