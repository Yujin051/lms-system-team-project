package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.RoleType;
import org.example.dto.LectNthDto;
import org.example.dto.LmsContsDto;
import org.example.dto.admin.LectDto;
import org.example.dto.admin.MemberDto;
import org.example.dto.admin.PostDto;
import org.example.dto.admin.StudLectProgDto;
import org.example.service.LectNthService;

import org.example.dto.ProfessorDto;
import org.example.service.admin.AdminProfInfoService;
import org.example.service.admin.AdminService;
import org.example.service.admin.AdminYoutubeService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final AdminYoutubeService adminYoutubeService;
    private final LectNthService lectNthService;
    private final AdminProfInfoService adminProfInfoService;

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

    /**
     * 관리자 - 학생관리
     * @author 임휘재
     */
    @GetMapping("/studentmanage")
    public String adminStudent(Model model) {
        List<MemberDto> student = adminService.getStudentInfo();
        List<MemberDto> studentOne = adminService.getBasicInfo();
//        MemberDto studCreCplAvg = adminService.getStudCreCplAvg();
        model.addAttribute("student", student);
        model.addAttribute("st", studentOne);
//        model.addAttribute("avg", studCreCplAvg);
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
            log.info("userGender : {}", dto.getUserGender());
            log.info("userAddr : {}", dto.getUserAddr());
            log.info("studNowCr : {}", dto.getStudNowCr());
            log.info("userId : {}", dto.getUserId());
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
    public List<MemberDto> studIdSearch(@RequestParam(value = "keyword") String keyword){
        log.info("keyword : " + keyword);
        return adminService.getFindByStudId(keyword);
    }

    /**
     * 관리자 - 학생관리 : 학생정보 이름, 학번 검색
     * @author 임휘재
     */
    @GetMapping("/studentmanage/all/api/search")
    @ResponseBody
    public List<MemberDto> allSearch(@RequestParam(value = "idKeyword", required = false) String idKeyword ,
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
    public List<MemberDto> noSearch(@RequestParam(value = "idKeyword", required = false) String idKeyword ,
                                     @RequestParam(value = "nameKeyword", required = false) String nameKeyword){
        log.info("idKeyword : " + idKeyword);
        log.info("nameKeyword : " + nameKeyword);
        return adminService.getNoSearch(idKeyword, nameKeyword);
    }

    @GetMapping("/profmanage")
    public String adminProf() {
        return "/admin/admin_prof_manage";
    }

    // 강사관리 페이지 로딩 시 전체 조회 후 그리드에 출력할 데이터 리턴
    @GetMapping("/getproflist")
    @ResponseBody
    public ResponseEntity adminProfList() {
        List<ProfessorDto> profList = adminProfInfoService.professorList();
        JSONObject object = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject pagination = new JSONObject();
        object.put("result", true);
        object.put("data", data);
        data.put("contents", profList);
        data.put("pagination", pagination);
        pagination.put("page", 1);
        pagination.put("totalCount", 12);

//        System.out.println(object.toString());
        return new ResponseEntity<>(object.toString(), HttpStatus.OK);
    }

    @PostMapping("/getproflist")
    @ResponseBody
    public ResponseEntity searchList(@RequestParam("active") String active,
                                     @RequestParam("subject") String subject,
                                     @RequestParam("name") String name) {
        List<ProfessorDto> profList = adminProfInfoService.professorConditionList(active, subject, name);

//        System.out.println(profList.toString());
        return new ResponseEntity<>(profList, HttpStatus.OK);
    }

    @PostMapping("/getdetail")
    @ResponseBody
    public ResponseEntity profDetail(@RequestParam("name") String name,
                                     @RequestParam("work") String work) {
        ProfessorDto professorDto = adminProfInfoService.professorDetails(work, name);
//        System.out.println(professorDto);
        return new ResponseEntity(professorDto, HttpStatus.OK);
    }


    /**
     * 관리자 : 전체관리성적 조회
     * @author 임휘재
     */
    @GetMapping("/grade")
    public String grade(){
        return "/admin/gradeManagement";
    }

    /**
     * 관리자 : 전체관리성적 : 학생정보 조회
     * @author 임휘재
     */
    @GetMapping("/api/grade")
    @ResponseBody
    public ResponseEntity<List<MemberDto>> adminGradeStudInfo() {
        List<MemberDto> dtos = adminService.getAdminGradeStudInfo();
        for(int i=0; i < dtos.size(); i++){
            MemberDto dto = dtos.get(i);
            log.info("gradeUserName : {}", dto.getUserName());
            log.info("userEmail : {}", dto.getUserEmail());
        }
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 - 전체관리성적 이름 검색
     * @author 임휘재
     */
    @GetMapping("/grade/userName/api/search")
    @ResponseBody
    public List<MemberDto> gradeUserNameSearch(@RequestParam(value = "keyword") String keyword){
        log.info("keyword : " + keyword);
        return adminService.getAdminGradeUserNameSearch(keyword);
    }

    /**
     * 관리자 - 전체관리성적 학번 검색
     * @author 임휘재
     */
    @GetMapping("/grade/studId/api/search")
    @ResponseBody
    public List<MemberDto> gradeStudIdSearch(@RequestParam(value = "keyword") String keyword){
        log.info("keyword : " + keyword);
        return adminService.getAdminGradeStudIdSearch(keyword);
    }

    /**
     * 관리자 - 전체관리성적 학년 검색
     * @author 임휘재
     */
    @GetMapping("/grade/studGrade/api/search")
    @ResponseBody
    public List<MemberDto> gradeStudGradeSearch(@RequestParam(value = "keyword") Long keyword){
        log.info("keyword : " + keyword);
        return adminService.getAdminGradeStudGradeSearch(keyword);
    }

    /**
     * 관리자 - 전체관리성적 이름, 학번 검색
     * @author 임휘재
     */
    @GetMapping("/grade/userIdAndUserName/api/search")
    @ResponseBody
    public List<MemberDto> getGradeFindUserIdAndUserName(@RequestParam(value = "idKeyword") String idKeyword,
                                                         @RequestParam(value = "nameKeyword") String nameKeyword){
        return adminService.getGradeFindUserIdAndUserName(idKeyword, nameKeyword);
    }

    /**
     * 관리자 - 전체관리성적 이름, 학번, 학년 검색
     * @author 임휘재
     */
    @GetMapping("/grade/all/api/search")
    @ResponseBody
    public List<MemberDto> gradeAllSearch(@RequestParam(value = "idKeyword", required = false) String idKeyword ,
                                     @RequestParam(value = "nameKeyword", required = false) String nameKeyword,
                                          @RequestParam(value = "gradeKeyword") Long gradeKeyword){
        log.info("idKeyword : " + idKeyword);
        log.info("nameKeyword : " + nameKeyword);
        log.info("gradeKeyword : " + gradeKeyword);
        return adminService.getAdminGradeAllSearch(idKeyword, nameKeyword, gradeKeyword);
    }

    /**
     * 관리자 - 전체관리성적 : 검색어가 비어있을때 조회버튼 누르면 전체 조회
     * @author 임휘재
     */
    @GetMapping("/grade/no/api/search")
    @ResponseBody
    public List<MemberDto> gradeNoSearch(@RequestParam(value = "idKeyword", required = false) String idKeyword ,
                                    @RequestParam(value = "nameKeyword", required = false) String nameKeyword,
                                         @RequestParam(value = "gradeKeyword", required = false) Long gradeKeyword){
        log.info("idKeyword : " + idKeyword);
        log.info("nameKeyword : " + nameKeyword);
        return adminService.getAdminGradeNoSearch(idKeyword, nameKeyword, gradeKeyword);
    }

    /**
     * 관리자 - 전체성적관리 : 전체 학기성적 및 전체 현황
     * @author 임휘재
     */
    @GetMapping("/api/gradeRecord")
    @ResponseBody
    public ResponseEntity<List<MemberDto>> allGradeRecord(@RequestParam(value = "studId") Long studId) {
        List<MemberDto> dtos = adminService.getAllGradeRecord(studId);
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 - 전체성적관리 : 강좌별성적
     * @author 임휘재
     */
    @GetMapping("/api/findGradeByCourse")
    @ResponseBody
    public ResponseEntity<List<MemberDto>> findGradeByCourse(@RequestParam(value = "studId") Long studId) {
        List<MemberDto> dtos = adminService.getFindGradesByCourse(studId);
        return ResponseEntity.ok(dtos);
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
     * 관리자 : 게시글 관리(담당용) 게시글 조회
     * @author 임휘재
     */
    @GetMapping("/api/postWrite")
    @ResponseBody
    public ResponseEntity<List<PostDto>> adminPostInfo() {
        List<PostDto> dtos = adminService.getPostInfo();
        for(int i = 0; i < dtos.size(); i++) {
            log.info("boardId : " + dtos.get(i).getBoardId());
            log.info("articleId : " + dtos.get(i).getArticleId());
        }
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 : 게시글 관리(담당용) 게시글 목록 게시판 종류 검색
     * @author 임휘재
     */
    @GetMapping("/api/postWrite/search/boardType")
    @ResponseBody
    public List<PostDto> postInfoBoardTypeSearch(@RequestParam(value = "keyword") String keyword){
        return adminService.getArticleFindByUserNameContainingIgnoreCase(keyword);
    }

    /**
     * 관리자 : 게시글 관리(담당용) 게시글 목록 검색조건 검색
     * @author 임휘재
     */
    @GetMapping("/api/postWrite/search/requirement")
    @ResponseBody
    public List<PostDto> postInfoRequirementSearch(@RequestParam(value = "keyword") String keyword,
                                                   @RequestParam(value = "searchType") String searchType){
        return adminService.getArticleSearchRequirement(keyword, searchType);
    }

    /**
     * 관리자 : 게시글 관리(담당용) 게시글 목록 작성일 검색
     * @author 임휘재
     */
    @GetMapping("/api/postWrite/search/articleAt")
    @ResponseBody
    public List<PostDto> postSearchArticleAt(@RequestParam(value = "keyword", required = false)
                                                 LocalDate keyword){
        log.info("articleAt : " + keyword);
        return adminService.getPostSearchArticleAt(keyword);
    }

    /**
     * 관리자 : 게시글 관리(담당용) 게시글 목록 게시글 종류, 검색조건으로 검색
     * @author 임휘재
     */
    @GetMapping("/api/postWrite/search/boardTypeAndArticleAt")
    @ResponseBody
    public List<PostDto> postSearchBoardTypeAndArticleAt(
            @RequestParam(value = "boardTypeKeyword") String boardTypeKeyword,
            @RequestParam(value = "searchType") String searchType,
            @RequestParam(value = "requirement") String requirement){
        return adminService.getPostSearchBoardTypeAndRequirement(boardTypeKeyword,
                searchType,requirement);
    }

    /**
     * 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판종류, 검색조건, 작성일 전부 검색
     * @author 임휘재
     */
    @GetMapping("/api/postWrite/search/all")
    @ResponseBody
    public List<PostDto> postAllSearch(@RequestParam(value = "boardTypeKeyword") String boardTypeKeyword,
                                       @RequestParam(value = "searchType") String searchType,
                                       @RequestParam(value = "requirement") String requirement,
                                       @RequestParam(value = "articleAt") LocalDate articleAt){
        log.info("boardTypeKeyword : " + boardTypeKeyword);
        return adminService.getPostAllSearch(boardTypeKeyword, searchType, requirement, articleAt);
    }

    /**
     * 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판종류, 검색조건, 작성일 비어있을때 전체검색
     * @author 임휘재
     */
    @GetMapping("/api/postWrite/search/no")
    @ResponseBody
    public List<PostDto> postNoSearch(@RequestParam(value = "boardTypeKeyword", required = false) String boardTypeKeyword,
                                      @RequestParam(value = "searchType", required = false) String searchType,
                                      @RequestParam(value = "requirement", required = false) String requirement,
                                      @RequestParam(value = "articleAt", required = false) LocalDate articleAt){
        log.info("boardTypeKeyword : " + boardTypeKeyword);
        return adminService.getPostNoSearch(boardTypeKeyword, searchType, requirement, articleAt);
    }

    /**
     * 관리자 - 게시글 작성(담당용) : 게시글 목록 조회
     * @author 임휘재
     */
    @GetMapping("/api/postWrite/postContentInfo")
    @ResponseBody
    public List<PostDto> PostContentInfo(@RequestParam(value = "articleId") Long articleId) {
        log.info("articleId : " + articleId);
        return adminService.getPostContents(articleId);
    }

    /**
     * 관리자 - 게시글 작성(담당용) : 게시글 목록 저장
     * @author 임휘재
     */
    @PutMapping("/api/postWrite/save")
    @ResponseBody
    public ResponseEntity<?> createArticle(@RequestBody PostDto postDto,
                                           RoleType ADMIN) {
        if(postDto.getArticleId() == null) {
            adminService.createArticle(postDto, ADMIN);
            log.info("saveArticleId : " + postDto.getArticleId());
        }else {
            adminService.updateArticle(postDto);
            log.info("updateArticleId : " + postDto.getArticleId());
        }

        List<PostDto> dtos = adminService.getPostInfo();
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 - 게시글 작성(담당용) : 게시글 목록 삭제
     * @author 임휘재
     */
    @DeleteMapping("/api/postWrite/delete")
    @ResponseBody
    public ResponseEntity<?> deleteArticle(@RequestParam(value = "articleId") Long articleId) {
        try {
            adminService.deleteArticle(articleId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("게시물 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시물 삭제 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 관리자 : 게시판 정보관리
     * @author 임휘재
     */
    @GetMapping("/postInfo")
    public String postInfo(){
        return "/admin/postInfo";
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 목록 조회
     * @author 임휘재
     */
    @GetMapping("/api/postInfo")
    @ResponseBody
    public ResponseEntity<List<PostDto>> boardInfo() {
        List<PostDto> dtos = adminService.getBoardInfo();
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 목록 게시판 번호 조회
     * @author 임휘재
     */
    @GetMapping("/api/postInfo/boardId/search")
    @ResponseBody
    public List<PostDto> boardInfoBoardIdSearch(@RequestParam(value = "boardId") Long boardId){
        return adminService.getBoardInfoBoardIdSearch(boardId);
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 목록 게시판 이름 조회
     * @author 임휘재
     */
    @GetMapping("/api/postInfo/boardName/search")
    @ResponseBody
    public List<PostDto> boardInfoBoardNameSearch(@RequestParam(value = "boardName") String boardName){
        return adminService.getBoardInfoBoardNameSearch(boardName);
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 목록 게시판 종류 조회
     * @author 임휘재
     */
    @GetMapping("/api/postInfo/boardType/search")
    @ResponseBody
    public List<PostDto> boardInfoBoardTypeSearch(@RequestParam(value = "boardType") String boardType){
        return adminService.getBoardInfoBoardTypeSearch(boardType);
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 목록 게시판 번호, 게시판 이름 조회
     * @author 임휘재
     */
    @GetMapping("/api/postInfo/boardIdAndBoardName/search")
    @ResponseBody
    public List<PostDto> boardInfoBoardIdAndBoardNameSearch(@RequestParam(value = "boardId") Long boardId,
                                                  @RequestParam(value = "boardName") String boardName){
        return adminService.getBoardInfoBoardIdAndBoardNameSearch(boardId,boardName);
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 목록 게시판 번호, 게시판 종류 조회
     * @author 임휘재
     */
    @GetMapping("/api/postInfo/boardIdAndBoardType/search")
    @ResponseBody
    public List<PostDto> boardInfoBoardIdAndBoardTypeSearch(@RequestParam(value = "boardId") Long boardId,
                                                            @RequestParam(value = "boardType") String boardType){
        return adminService.getBoardInfoBoardIdAndBoardTypeSearch(boardId,boardType);
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 목록 모든 검색어 조회
     * @author 임휘재
     */
    @GetMapping("/api/postInfo/all/search")
    @ResponseBody
    public List<PostDto> boardInfoAllSearch(@RequestParam(value = "boardId") Long boardId,
                                            @RequestParam(value = "boardType") String boardType,
                                            @RequestParam(value = "boardName") String boardName){
        return adminService.getBoardInfoAllSearch(boardId,boardType,boardName);
    }

    /**
     * 관리자 : 게시판 정보관리 : 게시판 정보 등록
     * @author 임휘재
     */
    @PutMapping("/api/postInfo/save")
    @ResponseBody
    public ResponseEntity<?> createBoard(@RequestBody PostDto postDto) {
        if(postDto.getBoardId() == null) {
            adminService.createBoard(postDto);
            log.info("saveBoardId : " + postDto.getBoardId());
        }else {
            adminService.updateBoard(postDto);
            log.info("updateBoardId : " + postDto.getBoardId());
        }
        List<PostDto> dtos = adminService.getBoardInfo();
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 - 게시글 작성(담당용) : 게시판 정보 삭제
     * @author 임휘재
     */
    @DeleteMapping("/api/postInfo/delete")
    @ResponseBody
    public ResponseEntity<?> deleteBoard(@RequestParam(value = "boardId") Long boardId) {
        try {
            adminService.deleteBoard(boardId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("게시물 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시물 삭제 중 오류 발생: " + e.getMessage());
        }
    }

    //온라인강의수강현황
    @GetMapping("/as")
    public String attendanceStatus() {
        return "/admin/attendance_status";
    }

    /**
     * 관리자 - 온라인강의수강현황 : 학습강좌조회
     * @author 임휘재
     */
    @GetMapping("/api/as")
    @ResponseBody
    public ResponseEntity<List<LectDto>> findLearningCourse() {
        List<LectDto> dtos = adminService.getFindLearningCourse();
        return ResponseEntity.ok(dtos);
    }

    /**
     * 관리자 - 온라인강의수강현황 : 학습강좌 강좌명으로 검색
     * @author 임휘재
     */
    @GetMapping("/api/as/lectName/search")
    @ResponseBody
    public List<LectDto> boardInfoBoardIdSearch(@RequestParam(value = "lectName") String lectName){
        return adminService.getLearningCourseLectNameSearch(lectName);
    }

    /**
     * 관리자 - 온라인강의수강현황 : 학습강좌 강좌운영상태로 검색
     * @author 임휘재
     */
    @GetMapping("/api/as/isActive/search")
    @ResponseBody
    public List<LectDto> boardInfoBoardIdSearch(@RequestParam(value = "isActive") Boolean isActive){
        return adminService.getLearningCourseIsActiveSearch(isActive);
    }

    /**
     * 관리자 - 온라인강의수강현황 : 학습강좌 강좌명, 강좌운영상태로 검색
     * @author 임휘재
     */
    @GetMapping("/api/as/all/search")
    @ResponseBody
    public List<LectDto> learningCourseAllSearch(@RequestParam(value = "lectName") String lectName,
                                                 @RequestParam(value = "isActive") Boolean isActive){
        return adminService.getLearningCourseAllSearch(lectName, isActive);
    }

    /**
     * 관리자 - 온라인강의수강현황 : 전체이수현황 조회
     * @author 임휘재
     */
    @GetMapping("/api/as/status")
    @ResponseBody
    public ResponseEntity<List<LectDto>> totalCompletionStatus(@RequestParam(value = "lectId") Long lectId) {
        List<LectDto> dtos = adminService.getTotalCompletionStatus(lectId);
        return ResponseEntity.ok(dtos);
    }


    //온라인강의콘텐츠관리
    @GetMapping("/ytr")
    public String youTubeRegistration(Model model) {
        StudLectProgDto dto = adminYoutubeService.getFindMagId();
        model.addAttribute("magId", dto.getMagId());
        log.info("magId : " + dto.getMagId());
        return "/admin/youTube_registration";
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

        log.info("LectNthDto::{}", lectNthDto);
        log.info("lectNthDtoId : {}", lectNthDto.getLectId());

        if (lectNthDto.getNthId() == null) {
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
    @DeleteMapping("/api/lectnth/delete")
    @ResponseBody
    public ResponseEntity<?> deleteLectNth(@RequestParam(value = "nthId") Long nthId) {
        try {
            lectNthService.deleteLectNth(nthId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("게시물 삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시물 삭제 중 오류 발생: " + e.getMessage());
        }
    }

}
