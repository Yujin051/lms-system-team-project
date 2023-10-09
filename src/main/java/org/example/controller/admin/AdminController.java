package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProfessorDto;
import org.example.service.admin.ProfInfoService;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProfInfoService profInfoService;

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

    // 강사관리 페이지 로딩 시 전체 조회 후 그리드에 출력할 데이터 리턴
    @GetMapping("/getproflist")
    @ResponseBody
    public ResponseEntity adminProfList() {
        List<ProfessorDto> profList = profInfoService.professorList();
        JSONObject object = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject pagination = new JSONObject();
        object.put("result", true);
        object.put("data", data);
        data.put("contents", profList);
        data.put("pagination", pagination);
        pagination.put("page", 1);
        pagination.put("totalCount", 12);

        System.out.println(object.toString());
        return new ResponseEntity<>(object.toString(), HttpStatus.OK);
    }

    @PostMapping("/getproflist")
    @ResponseBody
    public ResponseEntity searchList(@RequestParam("active") String active,
                                     @RequestParam("subject") String subject,
                                     @RequestParam("name") String name) {
        List<ProfessorDto> profList = profInfoService.professorConditionList(active, subject, name);

        System.out.println(profList.toString());
        return new ResponseEntity<>(profList, HttpStatus.OK);
    }

    @PostMapping("/getdetail")
    @ResponseBody
    public ResponseEntity profDetail(@RequestParam("name") String name,
                                     @RequestParam("work") String work) {
        ProfessorDto professorDto = profInfoService.professorDetails(work, name);
        System.out.println(professorDto);
        return new ResponseEntity(professorDto, HttpStatus.OK);
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
