package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.MemberDto;
import org.example.entity.Student;
import org.example.service.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

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
    public String adminStudent(Model model) {
        List<MemberDto> student = adminService.getStudentInfo();
        List<MemberDto> studentOne = adminService.getBasicInfo();
        MemberDto studCreCplAvg = adminService.getStudCreCplAvg();
        model.addAttribute("student", student);
        model.addAttribute("st", studentOne);
        model.addAttribute("avg", studCreCplAvg);
        return "/admin/admin_student_manage";
    }

    /**
     * 관리자 - 학생관리 : 학생정보 조회
     * @author 임휘재
     */
    @GetMapping("/api/st")
    @ResponseBody
    public ResponseEntity<List<MemberDto>> adminStudentApi() {
        List<MemberDto> dtos = adminService.getStudentInfo();
        for(int i=0; i < dtos.size(); i++){
            MemberDto dto = dtos.get(i);
            log.info("studId : {}", dto.getStudId());
            log.info("userName : {}", dto.getUserName());
            log.info("userBirthday : {}", dto.getUserBirthday());
            log.info("userPhoneNum : {}", dto.getUserPhoneNum());
            log.info("userEmail : {}", dto.getUserEmail());
        }
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 - 학생관리 : 학생정보 이름검색
     * @author 임휘재
     */
    @GetMapping("/studentmanage/userName/api/search")
    @ResponseBody
    public List<MemberDto> userNameSearch(@RequestParam(value = "keyword") String keyword){
        log.info("keyword : " + keyword);
        return adminService.getFindUserNameContaining(keyword);
    }

    /**
     * 관리자 - 학생관리 : 학생정보 학번검색
     * @author 임휘재
     */
    @GetMapping("/studentmanage/studId/api/search")
    @ResponseBody
    public List<MemberDto> studIdSearch(@RequestParam(value = "keyword") Long keyword){
        log.info("keyword : " + keyword);
        return adminService.getFindByStudId(keyword);
    }

    /**
     * 관리자 - 학생관리 : 학생정보 이름, 학번 검색
     * @author 임휘재
     */
    @GetMapping("/studentmanage/all/api/search")
    @ResponseBody
    public List<MemberDto> allSearch(@RequestParam(value = "idKeyword", required = false) Long idKeyword ,
                                     @RequestParam(value = "nameKeyword", required = false) String nameKeyword){
        log.info("idKeyword : " + idKeyword);
        log.info("nameKeyword : " + nameKeyword);
        return adminService.getFindByStudIdAndUserName(idKeyword, nameKeyword);
    }

    /**
     * 관리자 - 학생관리 : 검색어가 비어있을때 조회버튼 누르면 전체 조회
     * @author 임휘재
     */
    @GetMapping("/studentmanage/no/api/search")
    @ResponseBody
    public List<MemberDto> noSearch(@RequestParam(value = "idKeyword", required = false) Long idKeyword ,
                                     @RequestParam(value = "nameKeyword", required = false) String nameKeyword){
        log.info("idKeyword : " + idKeyword);
        log.info("nameKeyword : " + nameKeyword);
        return adminService.getNoSearch(idKeyword, nameKeyword);
    }


    @GetMapping("/studentmanage/api/info/{memberId}")
    @ResponseBody
    public ResponseEntity<?> studInfo(@PathVariable("memberId") Long memberId) {
        log.info("memberId: " + memberId);
        try {
            MemberDto memberDto = adminService.getFindMemberInfo(memberId);

            log.info("memberId : " + memberId);
            return ResponseEntity.ok(memberDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 가져오기 오류");
        }
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
        return "/admin/attendance_status";
    }

    //온라인강의정보관리
    @GetMapping("/ttr")
    public String thisTime() {
        return "/admin/thisTime_registration";
    }

    //온라인강의콘텐츠관리
    @GetMapping("/ytr")
    public String youTubeRegistration() {

        return "/admin/youTube_registration";
    }
}
