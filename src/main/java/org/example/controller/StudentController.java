package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CheckGradeDto;
import org.example.dto.CheckSemGradeDto;
import org.example.entity.*;
import org.example.repository.LectInfoRepository;
import org.example.repository.StudLectApplyRepository;
import org.example.repository.StudentRepository;
import org.example.service.MemberService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

//    private LectInfoRepository lectInfoRepository;

    private final MemberService memberService;
    private final StudentService studentService;
    private final StudLectApplyRepository studLectApplyRepository;
    private final StudentRepository studentRepository;
    private final LectInfoRepository lectInfoRepository;

    // 학생 메인 페이지
    @GetMapping("")
    public String stuMain() {
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
    public String studentLecture() {
        return "/student/mylecture";
    }

    // 학생 강의계획서 보기
    // 수정필요
    @GetMapping("/lectureplan")
    public String studentPlan() {
        return "/student/lecturePlan";
    }

    // 학생 마이페이지
    @GetMapping("/mypage")
    public String studentMyPage() {
        return "/student/stud_my_page";
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
    @GetMapping("/assi")
    public String assiView(){
        return "/student/assiView";
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

                    studLectApplyRepository.save(studLectApply);

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

        studLectApplyRepository.deleteById(applyId);




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
