package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LectInfoDto;
import org.example.dto.LectNthDto;
import org.example.dto.LmsContsDto;
import org.example.dto.AddLmsContsRequestDto;
import org.example.entity.LectInfo;
import org.example.entity.LectNth;
import org.example.service.LectNthService;
import org.springframework.http.HttpStatus;
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
     *
     * @author 임휘재
     */
    @GetMapping("/grade")
    public String grade() {
        return "/admin/gradeManagement";
    }

    /**
     * 관리자 : 게시글 관리(담당용)
     *
     * @author 임휘재
     */
    @GetMapping("/postWrite")
    public String postWrite() {
        return "/admin/postWrite";
    }

    /**
     * 관리자 : 게시글 정보관리
     *
     * @author 임휘재
     */
    @GetMapping("/postInfo")
    public String postInfo() {
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
    public String thisTime(Model model) {
        List<LectNthDto> lectNthDto = lectNthService.getFindLectInfo2();
        model.addAttribute("lectId", lectNthDto);
        return "/admin/thisTime_registration";
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

    @GetMapping("/api/ttr/lectInfo")
    @ResponseBody
    public ResponseEntity<List<LectNthDto>> getFindLectInfo2() {
        List<LectNthDto> lectInfoDtos = lectNthService.getFindLectInfo2();
        return ResponseEntity.ok(lectInfoDtos);
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


    /* 강의 차시정보 하단 왼쪽 첫번째 테이블 비동기 처리 (grid 클릭했을 때 grid2 에 표시하게 하는 Controller)*/
    @GetMapping("/api/nthName/search")
    @ResponseBody
    public List<LectNthDto> lectIdSearch(@RequestParam(value = "lectId") Long lectId) {
        return lectNthService.getFindLectId(lectId);
    }

    /* 강의 차시정보 하단 우측 세번째 테이블 */
    @GetMapping("/api/contsName/search")
    @ResponseBody
    public List<LmsContsDto> contsNameSearch(@RequestParam(value = "nthId") Long nthId) {
                                                     // 오전 10시에 contsNo를 nthId 로 변경함
        log.info("contsNo : " + nthId);
        List<LmsContsDto> dtos = lectNthService.getFindContsNo(nthId);
        log.info("dtos : " + dtos);
        for (int i = 0; i < dtos.size(); i++) {

        }
        return dtos;
    }

    /* 강의 차시정보 저장 */
    @PutMapping("/api/lectnth/save")
    @ResponseBody
    public ResponseEntity<?> createLectNth(@RequestBody LectNthDto lectNthDto) {

        log.info("LectNthDto::{}",lectNthDto);
        log.info("lectNthDtoId : {}", lectNthDto.getLectId());

        if(lectNthDto.getNthId() == null) {
            lectNthService.createLectNth(lectNthDto);
            log.info("getNthName1 : " + lectNthDto.getNthName());
        } else {
            lectNthService.updateLectNth(lectNthDto);
            log.info("getNthName2 : " + lectNthDto.getNthName());
        }
        List<LectNthDto> dtos = lectNthService.getFindLectInfo2();

        return ResponseEntity.ok(dtos);

    }



    /* 삭제 기능 구현 */

/*    @PostMapping("/delete")
    public ResponseEntity<List<LectNthDto>> getFindLectInfoDelete() {
        List<LectNthDto> de = lectNthService.getFindLectInfoDelete();
        return ResponseEntity.ok(de);
    }*/
/*    @PostMapping("/delete")
    public ResponseEntity<List<LectNthDto>> getFindLectInfoDelete() {
        List<LectNthDto> de = lectNthService.getFindLectInfoDelete();
        // 여기에서 선택한 ID 목록에 해당하는 열만 삭제하는 작업 수행
        // 예를 들어, lectNthService.deleteRows(idsToDelete);
        return ResponseEntity.ok(de);
    }*/

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody List<Long> idsToDelete) {
        // 여기에서 선택한 ID 목록에 해당하는 열만 삭제하는 작업 수행
        // 예를 들어, lectNthService.deleteRows(idsToDelete);
        return ResponseEntity.ok("선택한 항목을 삭제했습니다.");
    }



}