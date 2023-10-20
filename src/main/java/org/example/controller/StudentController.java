package org.example.controller;

import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.entity.LectInfo;
import org.example.entity.Member;
import org.example.repository.AssignmentsRepository;
import org.example.repository.MemberRepository;
import org.example.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.example.entity.*;
import org.example.repository.GradeInfoRepository;
import org.example.repository.LectInfoRepository;
import org.example.repository.StudLectApplyRepository;
import org.example.repository.StudentRepository;
import org.example.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {


    private final LectureService lectureService;
    private final AssignmentsRepository assignmentsRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final AssignmentsService assignmentsService;
    private final StudentService studentService;
    private final StudLectApplyRepository studLectApplyRepository;
    private final StudentRepository studentRepository;
    private final LectInfoRepository lectInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;
    private final AssignSubmitService assignSubmitService;

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
    public String studentGrade(Model model , Principal principal) {
        // 사용자 loginId 가져오기
        String loginId = principal.getName();
        // 사용자 정보 가져오기(member)
        Member member = memberService.memberView(loginId);
        // member 뭐 들었는지 제대로 들어왔는지 확인.
        System.out.println("member = " + member.toString());
        // id 넣어서 서비스 이용하기
        model.addAttribute("list", studentService.gradeCheckList(member.getId()));
        model.addAttribute("list2", studentService.semGradeCheckList(member.getId()));
        return "/student/gradecheck";
    }

    @RequestMapping(value = "/grade/find", method = RequestMethod.POST)
    @ResponseBody
    public List<CheckGradeDto> findDetailSemGrade(Principal principal, @RequestBody CheckSemGradeDto checkSemGradeDto){
        System.out.println(checkSemGradeDto);
        // 사용자 loginId 가져오기
        String loginId = principal.getName();
        // 사용자 정보 가져오기(member)
        Member member = memberService.memberView(loginId);

        List<CheckGradeDto> CheckGradeInfoList = studentService.detailGradeCheckList(member.getId(), checkSemGradeDto.getSemYear(), checkSemGradeDto.getSemSem());


        return CheckGradeInfoList;
    }

    // 학생 나의 강의실
    @GetMapping("/lecture")
    public String studentLecture(Principal principal, Model model) {
        Member member = memberRepository.findByUserId(principal.getName());
        Long id =  member.getId();
        model.addAttribute("memberId", id);

        System.out.println(lectureService.findCoursesByMemberAndSemester(id, "2023", "2학기"));
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

    @GetMapping("/onlineclass")
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



    @GetMapping("/lecture/view/{lectId}/assignments")
    public String assignment(@PathVariable("lectId") Long lectId, Model model) {
        model.addAttribute("lectId", lectId);
        LectInfo lectInfo = lectInfoRepository.findByLectId(lectId);
        List<Assignments> assignmentsList = assignmentsRepository.findByLectInfoLectId(lectId);

//        List<AssignSubmit> submissions = assignSubmitService.getSubmissionsByLectId(lectId);
        model.addAttribute("lectInfo", lectInfo);
        model.addAttribute("assignmentsList", assignmentsList);
//        model.addAttribute("submissions", submissions);
        return "/student/assignment"; // Thymeleaf 템플릿 경로 수정
    }

    @GetMapping("/lecture/view/{lectId}/assignments/{assiId}/submit")
    public String addAssignment2(@PathVariable("lectId") Long lectId, @PathVariable("assiId") Long assiId, Model model) {
        LectInfo lectInfo = lectInfoRepository.findByLectId(lectId);
        Assignments assignments = assignmentsRepository.findByAssiId(assiId);
        model.addAttribute("lectId", lectInfo.getLectId());
        model.addAttribute("assiId", assignments.getAssiId());
        model.addAttribute("lectName", lectInfo.getLectName());
        model.addAttribute("assignment", new AssignmentSubmitDto());
        return "student/assiWrite";
    }

    @PostMapping("/lecture/view/{lectId}/assignments/{assiId}/submit/submit")
    public String addAssignmentSubmit(@PathVariable("lectId")Long lectId, @PathVariable("assiId")Long assiId, @Validated AssignmentSubmitDto assignmentSubmitDto, @RequestPart MultipartFile file, Model model) throws Exception {
        try {
            AssignSubmit assignSubmit = AssignSubmit.createAssignmentSubmit(assignmentSubmitDto);
            assignSubmitService.saveAssignSubmit(assignSubmit);

            model.addAttribute("message", "과제가 제출되었습니다.");
        } catch (Exception e) {
            model.addAttribute("error", "과제 추가 중 오류가 발생했습니다.");
        }
        return "student/stud_main";
    }



    @GetMapping("/scr")
    public String courseRegisteration(Model model, Principal principal) {
        // 사용자 로그인 아이디 가져오기
        String loginId = principal.getName();
        // 사용자 정보 가져오기(member)
        Member member = memberService.memberView(loginId);
        Student student = studentRepository.findByMember(member);

        model.addAttribute("list", studentService.lectInfoList());
        model.addAttribute("list1", studentService.studLectApplyCheckList(member.getId()));
        model.addAttribute("student", student);
        return "/student/course_registeration";
    }

    @PostMapping("/scr/my")
    public String courseRegisterationMy(Model model, Principal principal, @RequestParam("lectId") Long lectId, @RequestParam("lectCredit") Long lectCredit) {
        // 사용자 로그인 아이디 가져오기
        String loginId = principal.getName();
        // 사용자 정보 가져오기(member)
        Member member = memberService.memberView(loginId);

        Student student = studentRepository.findByMember(member);
        LectInfo lectInfo = lectInfoRepository.findById(lectId).orElseThrow();

        boolean alreadyRegistered = studLectApplyRepository.existsByStudentAndLectInfo(student, lectInfo);


        if (!alreadyRegistered) {
            if (lectInfo.getLectNownum() < lectInfo.getLectMaxnum()) {
                if (student.getStudNowCr() + lectCredit <= student.getStudMaxCr()) {
                    lectInfo.Plus(); // 강좌에 신청학생수 1증가
                    student.setStudNowCr(student.getStudNowCr() + lectCredit);

                    StudLectApply studLectApply = new StudLectApply(student, lectInfo);

                    StudLectApply stud1 = studLectApplyRepository.save(studLectApply);

                    GradeInfo gradeInfo = GradeInfo.builder()
                            .studLectApply(stud1)
                            .build();

                    gradeInfoRepository.save(gradeInfo);
                    model.addAttribute("message", "신청되었습니다.");
                    model.addAttribute("SearchUrl", "/student/scr");
                    model.addAttribute("list", studentService.lectInfoList());
                    model.addAttribute("list1", studentService.studLectApplyCheckList(member.getId()));
                    return "Message";

                } else {
                    model.addAttribute("message", "들을수있는 학점을 넘으셨습니다.");
                    model.addAttribute("SearchUrl", "/student/scr");
                    return "Message";
                }
                } else {
                    model.addAttribute("message", "제한신청수를 넘었습니다.");
                    model.addAttribute("SearchUrl", "/student/scr");
                    return "Message";
                }
            } else {
                model.addAttribute("message", "이미 수강신청한 강좌입니다.");
                model.addAttribute("SearchUrl", "/student/scr");
                return "Message";
            }

        }




    @PostMapping("/scr/myDelete")
    public String courseRegisterationMyDelete(Model model, Principal principal,  @RequestParam("lectId") Long lectId, @RequestParam("applyId") Long applyId, @RequestParam("lectCredit") Long lectCredit) {
        // 사용자 로그인 아이디 가져오기
        String loginId = principal.getName();
        // 사용자 정보 가져오기(member)
        Member member = memberService.memberView(loginId);
        Student student = studentRepository.findByMember(member);
        LectInfo lectInfo = lectInfoRepository.findById(lectId).orElseThrow();


        lectInfo.minus();
        student.setStudNowCr(student.getStudNowCr() - lectCredit);

        StudLectApply studLectApply = studLectApplyRepository.findById(applyId).orElse(null);

        if (studLectApply != null) {
            // 부모 엔티티에서 자식 엔티티를 가져옴
            GradeInfo gradeInfos = gradeInfoRepository.findByStudLectApply(studLectApply);

            if (gradeInfos != null) {
                // 자식 엔티티를 삭제할게요.
                gradeInfoRepository.delete(gradeInfos);
            }
            // 부모엔티티를 삭제할게요.
            studLectApplyRepository.deleteById(applyId);
        }




        model.addAttribute("message", "취소되었습니다.");
        model.addAttribute("SearchUrl", "/student/scr");
        model.addAttribute("list", studentService.lectInfoList());
        model.addAttribute("list1", studentService.studLectApplyCheckList(member.getId()));

        return "Message";
    }


    /* 학생 나의 강의실 */
    @GetMapping("/mc")
    public String myClassroom() {
        return "/student/my_classroom";
    }
}
