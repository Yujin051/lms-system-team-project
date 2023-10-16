package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.constant.Gender;
import org.example.dto.LectInfoDTO;
import org.example.entity.LectInfo;
import org.example.entity.Member;
import org.example.repository.AssignmentsRepository;
import org.example.repository.MemberRepository;
import org.example.service.AssignmentsService;
import org.example.service.LectureService;
import org.example.service.MemberService;
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
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {


    @Autowired
    private LectureService lectureService;

    @Autowired
    private final AssignmentsRepository assignmentsRepository;

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final AssignmentsService assignmentsService;
    // 학생 메인 페이지
    @GetMapping("")
    public String stuMain(Principal principal, Model model) {
        // 학생 프로필에 이름 띄우기
        Member member = memberRepository.findByUserId(principal.getName());
        String name = (member != null) ? member.getUserName() : "Unknown";
        String savedProfile = (member != null) ? member.getImgSaved() : "Unknown";
        model.addAttribute("name", name);
        model.addAttribute("profileImg", savedProfile);
        System.out.println(savedProfile);

        return "/student/stud_main";
    }

    // 학생 성적 페이지
    @GetMapping("/grade")
    public String studentGrade() {
        return "/student/gradecheck";
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

    // 학생 나의 강의실(특정 강좌)
    @GetMapping("/lecture/view/{id}")
    public String lectureView(Model model, @PathVariable("id")long id) {
        model.addAttribute("lectinfo", lectureService.lectureView(id));
        return "/student/lecturemain";
    }

    // 학생 마이페이지
    @GetMapping("/mypage")
    public String studentMyPage(Model model, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Member member = memberRepository.findByUserId(userDetails.getUsername());
        model.addAttribute("member", member);
        return "/student/stud_my_page";
    }
    // 학생 마이페이지 수정기능
    @PostMapping("/mypage/modify")
    public String studentUpdate(Member member, Principal principal, @RequestPart MultipartFile file, Model model) throws Exception {
        Member memberT = memberService.memberView(principal.getName());
        memberT.setUserName(member.getUserName());
        memberT.setUserBirthday(member.getUserBirthday());
        memberT.setUserPhoneNum(member.getUserPhoneNum());
        memberT.setUserEmail(member.getUserEmail());
        memberT.setUserAddr(member.getUserAddr());

        //memberT.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.updateMember(memberT, file);

        model.addAttribute("message", "정보가 수정되었습니다.");
        model.addAttribute("SearchUrl", "/student");
        return "/student/message";
    }

    @GetMapping("/testattendance")
    public String test1() {
        return "/student/attendance";
    }

    @GetMapping("/test")
    public String test2() {
        return "/student/onlineclass";
    }

    /**
     * 학생 : 과제출제
     * @author 임휘재
     */
    @GetMapping("/lecture/view/{lectId}/assignments/{assiId}")
    public String assiView(@PathVariable("lectId")long lectId, @PathVariable("assiId")long assiId, Model model, Principal principal){
        Member member = memberRepository.findByUserId(principal.getName());
        String name = (member != null) ? member.getUserName() : "Unknown";

        model.addAttribute("assignments",assignmentsRepository.findAssignmentByLectIdAndAssignId(lectId, assiId));
        model.addAttribute("membername", name);

        System.out.println("name is "+name);
        System.out.println("컨트롤러테스트 "+List.of(assignmentsRepository.findAssignmentByLectIdAndAssignId(lectId, assiId)));
        System.out.println("lectId :"+ lectId + "assiId : "+ assiId);
        return "/student/assiView";
    }

    @GetMapping("/scr")
    public String courseRegisteration() {
        return "/student/course_registeration";
    }


    /* 학생 나의 강의실 */
    @GetMapping("/mc")
    public String myClassroom() {
        return "/student/my_classroom";
    }
}
