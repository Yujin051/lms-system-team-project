package org.example.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.dto.LectInfoDTO;
import org.example.dto.ProfessorDto;
import org.example.entity.LectInfo;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.repository.AssignmentsRepository;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
import org.example.service.LectureService;
import org.example.service.MemberService;
import org.example.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
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
    @Autowired
    private final AssignmentsRepository assignmentsRepository;
    private final LectureService lectureService;

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
        memberT.setUserName(member.getUserName());
        memberT.setUserBirthday(member.getUserBirthday());
        memberT.setUserPhoneNum(member.getUserPhoneNum());
        memberT.setUserEmail(member.getUserEmail());
        memberT.setUserAddr(member.getUserAddr());

        memberService.updateMember(memberT, file);

        Professor professorT = professorService.ProfessorView(memberT.getId());

        professorT.setProfBank(professor.getProfBank());
        professorT.setProfWork(professor.getProfWork());
        professorT.setProfAccount(professor.getProfAccount());
        professorT.setProfAgency(professor.getProfAgency());

        professorService.professorUpdate(professorT);

        model.addAttribute("message", "정보가 수정되었습니다.");
        model.addAttribute("SearchUrl", "/prof");
        return "/student/message";
    }

    // 학생 나의 강의현황
    @GetMapping("/lecture")
    public String studentLecture(Principal principal, Model model) {
        Member member = memberRepository.findByUserId(principal.getName());
        Long id =  member.getId();
        model.addAttribute("memberId", id);

        List<LectInfo> lectInfoList = lectureService.findCoursesByMemberAndSemester(id, "2023", "2학기");
        model.addAttribute("lectInfoList", lectInfoList);

        return "/student/myLecture";
    }

    // 학생 나의 강의현황 > 강좌검색
    @RequestMapping(value = "/lecture/find", method = RequestMethod.GET)
    @ResponseBody
    public List<LectInfoDTO> findCoursesByMemberAndSemester(@RequestParam Long memberId, @RequestParam String year, @RequestParam String semester) {
        List<LectInfo> lectInfoList = lectureService.findCoursesByMemberAndSemester(memberId, year, semester);

        List<LectInfoDTO> lectInfoDTOList = new ArrayList<>();
        for (LectInfo lectInfo : lectInfoList) {
            lectInfoDTOList.add(LectInfoDTO.fromLectInfo(lectInfo));
        }
        return lectInfoDTOList;
    }

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

    @GetMapping("/lecture/view/{lectId}/assignments/add")
    public String addAssignment(@PathVariable("lectId") Long lectId, Model model) {
        model.addAttribute("lectId", lectId);
        return "prof/assiSmInfo";
    }

    @GetMapping("/test")
    public String proftest(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Member member = memberRepository.findByUserId(userDetails.getUsername());
        System.out.println(professorRepository.findByProfessorId(member.getId()));

        return "";
    }


}
