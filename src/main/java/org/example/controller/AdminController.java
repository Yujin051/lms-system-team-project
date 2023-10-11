package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AttendanceStatusDto;
import org.example.dto.LectNthDto;
import org.example.entity.LectNth;
import org.example.repository.AdminThisTimeRegisTration;
import org.example.service.AdminAttendanceStatusService;
import org.example.service.LectNthService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
    public String thisTime(String searchType, Boolean nthKeyword) {
        List<LectNthDto> nthList = lectNthService.lectNthList(searchType, nthKeyword);
        return "/admin/thisTime_registration";
    }

    /* 온라인 차시정보 데이터 값 다른 테이블에 불러오기  */
    @GetMapping("/ttr/api")
    @ResponseBody
    public ResponseEntity<List<LectNthDto>> findLectNthDtos(String searchType, Boolean nthKeyword) {
        List<LectNthDto> lectNthData = lectNthService.getLectNthData(searchType, nthKeyword);

        log.info(lectNthData.toString());

        return ResponseEntity.ok(lectNthData);
    }



    /* 온라인 차시정보 데이터 값 테이블에 불러오기  */
    @GetMapping("/ttr/search")
    @ResponseBody
    public ResponseEntity<List<LectNthDto>> adminLectNth(String searchType, Boolean nthKeyword) {
        List<LectNthDto> lectNthDtos = lectNthService.lectNthList(searchType, nthKeyword);
//        log.info(lectNthDtos.toString());
        return ResponseEntity.ok(lectNthDtos);
    }
    /* 운영 상태 드롭박스 검색 */
    @GetMapping("/api/ttr/isActive/search")
    @ResponseBody
    public List<LectNthDto> findLectNthBox(@RequestParam(value = "isActive", required = false) Boolean isActive) {
        log.info("isAc : " + isActive);
        return lectNthService.getFindLectNthBox(isActive);
    }
    /* 강좌명 검색 */
    @GetMapping("/api/ttr/lectName/search")
    @ResponseBody
    public List<LectNthDto> findLectName(@RequestParam(value = "lectName") String lectName) {
        log.info("isAc : " + lectName);
        return lectNthService.getFindLectName(lectName);
    }




    //온라인강의콘텐츠관리
    @GetMapping("/ytr")
    public String youTubeRegistration() {
        return "/admin/youTube_registration";
    }

    //lectSubject 검색
    @GetMapping("/api/lectName/search")
    @ResponseBody
    public List<LectNthDto> lectNameSearch(@RequestParam(value = "lectName") String lectName,
                                              @RequestParam(value = "isActive") Boolean isActive) {
        return lectNthService.getFindLectNthSearch(lectName, isActive);
    }



    /* 강의 차시정보 하단테이블 비동기 처리 */
    @GetMapping("/api/nthName/search")
    @ResponseBody
    public List<LectNthDto> nthNameSearch(@RequestParam(value = "nthName") String nthName) {
        return lectNthService.getFindNtnName(nthName);
    }


}
