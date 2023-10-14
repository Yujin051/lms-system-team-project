package org.example.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.RoleType;
import org.example.dto.admin.LectDto;
import org.example.dto.admin.MemberDto;
import org.example.dto.admin.PostDto;
import org.example.dto.admin.StudLectProgDto;
import org.example.entity.*;
import org.example.repository.admin.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final BoardArticleRepository articleRepository;
    private final BoardInfoRepository boardInfoRepository;
    private final LectInfoRepository lectInfoRepository;
    private final StudLectProgRepository studLectProgRepository;

    //관리자 - 학생관리 : 학생정보
    public List<MemberDto> getStudentInfo(){
        return adminRepository.findMembersWithDto();
    }

    //관리자 - 학생관리 : 기본정보
    public List<MemberDto> getBasicInfo(){
        return adminRepository.findMemberDtoOne();
    }

    //관리자 - 학생관리 : 평균학점
    public MemberDto getStudCreCplAvg(){
        return adminRepository.findStudCreCplAvg();
    }

    //관리자 - 학생관리 : 이름검색
    public List<MemberDto> getFindUserNameContaining(String keyword){
        return adminRepository.findByUserNameContainingIgnoreCase(keyword);
    }

    //관리자 - 학생관리 : 학번검색
    public List<MemberDto> getFindByStudId(String Keyword){
        return adminRepository.findByStudId(Keyword);
    }

    //관리자 - 학생관리 : 이름, 학번 검색
    public List<MemberDto> getFindByStudIdAndUserName(String idKeyword, String nameKeyword){
        return adminRepository.findByStudIdAndUserName(idKeyword, nameKeyword);
    }

    //관리자 - 학생관리 : 검색어가 비어있을때 조회버튼 누르면 전체 조회
    public List<MemberDto> getNoSearch(String idKeyword, String nameKeyword){
        return adminRepository.noSearchByNameAndStudId(nameKeyword, idKeyword);
    }

    // 관리자 - 전체성적관리 : 학생정보
    public List<MemberDto> getAdminGradeStudInfo(){
        return adminRepository.findGradeStudInfo();
    }

    //관리자 - 전체성적관리 : 이름검색
    public List<MemberDto> getAdminGradeUserNameSearch(String keyword){
        return adminRepository.gradeFindByUserNameContainingIgnoreCase(keyword);
    }

    //관리자 - 전체성적관리 : 학번검색
    public List<MemberDto> getAdminGradeStudIdSearch(String keyword){
        return adminRepository.gradeFindByStudId(keyword);
    }

    //관리자 - 전체성적관리 : 학년검색
    public List<MemberDto> getAdminGradeStudGradeSearch(Long keyword){
        return adminRepository.gradeFindByStudGrade(keyword);
    }

    //관리자 - 전체성적관리 : 이름, 학번 검색
    public List<MemberDto> getGradeFindUserIdAndUserName(String idKeyword, String nameKeyword){
        return adminRepository.gradeFindUserIdAndUserName(idKeyword, nameKeyword);
    }

    //관리자 - 전체성적관리 : 이름, 학번, 학년 전체 검색
    public List<MemberDto> getAdminGradeAllSearch(String idKeyword, String nameKeyword, Long gradeKeyword){
        return adminRepository.gradeFindAllSearch(idKeyword, nameKeyword, gradeKeyword);
    }

    //관리자 - 전체성적관리 : 검색어가 비어있을 때 전체 조회되게
    public List<MemberDto> getAdminGradeNoSearch(String idKeyword, String nameKeyword, Long gradeKeyword) {
        return adminRepository.gradeNoSearch(idKeyword, nameKeyword, gradeKeyword);
    }

    //관리자 - 전체성적관리 : 전체 학기성적 및 전체 현황
    public List<MemberDto> getAllGradeRecord(Long studId) {
        return adminRepository.allGradeRecord(studId);
    }


    // 관리자 - 전체성적관리 : 강좌별성적
    public List<MemberDto> getFindGradesByCourse(Long studId) {
        return adminRepository.findGradesByCourse(studId);
    }


    // 관리자 - 게시글 작성(담당용) : 게시글 목록 조회
    public List<PostDto> getPostInfo() {
        return adminRepository.postInfo();
    }

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판 종류 검색
    public List<PostDto> getArticleFindByUserNameContainingIgnoreCase(String keyword){
        return adminRepository.articleFindByUserNameContainingIgnoreCase(keyword);
    }

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 검색조건 검색
    public List<PostDto> getArticleSearchRequirement(String keyword, String searchType){
        log.info("serviceKeyword : " + keyword);
        return adminRepository.articleSearchRequirement(keyword, searchType);
    }

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 작성일 검색
    public List<PostDto> getPostSearchArticleAt(LocalDate keyword){
        return adminRepository.postSearchArticleAt(keyword);
    }

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판종류, 검색조건으로 검색
    public List<PostDto> getPostSearchBoardTypeAndRequirement(String boardTypeKeyword,
                                                              String searchType,
                                                              String requirement) {
        return adminRepository.postSearchBoardTypeAndRequirement(boardTypeKeyword,
                searchType,requirement);
    }

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판종류, 검색조건, 작성일 전부 검색
    public List<PostDto> getPostAllSearch(String boardTypeKeyword,
                                          String searchType,
                                          String requirement,
                                          LocalDate articleAt) {
        return adminRepository.postAllSearch(boardTypeKeyword, searchType, requirement, articleAt);
    }

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판종류, 검색조건, 작성일 비어있을때 검색
    public List<PostDto> getPostNoSearch(String boardTypeKeyword,
                                         String searchType,
                                         String requirement,
                                         LocalDate articleAt) {
        return adminRepository.postNoSearch(boardTypeKeyword, searchType, requirement, articleAt);
    }


    //관리자 - 게시글 작성(담당용) : 게시글 내용 조회
    public List<PostDto> getPostContents(Long articleId){
        return adminRepository.postContents(articleId);
    }

    //관리자 - 게시글 작성(담당용) : 등록기능
    @Transactional
    public BoardArticle createArticle(PostDto postDto, RoleType userRole) {

        BoardInfo boardInfo = boardInfoRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("게시판 정보를 찾을 수 없습니다"));


        Member member = adminRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("memberId오류"));

        postDto.setMember(member);
        postDto.setBoardInfo(boardInfo);

        return articleRepository.save(postDto.toEntity());
    }

    // 관리자 - 게시글 작성(담당용) : 수정기능
    @Transactional
    public BoardArticle updateArticle(PostDto postDto) {
        // 1. 게시물 ID를 사용하여 해당 게시물을 조회합니다.
        BoardArticle existingArticle = articleRepository.findById(postDto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));

        // 2. 수정할 필드를 업데이트합니다.
        existingArticle.setArticleTitle(postDto.getArticleTitle());
        existingArticle.setArticleContent(postDto.getArticleContent());
        existingArticle.setBoardInfo(postDto.getBoardInfo());
        existingArticle.setIsLocked(postDto.getIsLocked());
        existingArticle.setIsDeleted(postDto.getIsDeleted());

        // 3. 수정된 엔티티를 저장합니다.
        return articleRepository.save(existingArticle);
    }

    //관리자 - 게시글 작성(담당용) : 삭제기능
    @Transactional
    public void deleteArticle(Long articleId) {
        BoardArticle boardArticle = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        articleRepository.delete(boardArticle);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 조회
    public List<PostDto> getBoardInfo() {
        return adminRepository.boardInfo();
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 번호 조회
    public List<PostDto> getBoardInfoBoardIdSearch(Long boardId) {
        return adminRepository.boardInfoBoardIdSearch(boardId);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 이름 조회
    public List<PostDto> getBoardInfoBoardNameSearch(String boardName) {
        return adminRepository.boardInfoBoardNameSearch(boardName);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 종류 조회
    public List<PostDto> getBoardInfoBoardTypeSearch(String boardType) {
        return adminRepository.boardInfoBoardTypeSearch(boardType);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 번호, 게시판 이름 조회
    public List<PostDto> getBoardInfoBoardIdAndBoardNameSearch(Long boardId, String boardName) {
        return adminRepository.boardInfoBoardIdAndBoardNameSearch(boardId, boardName);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 번호, 게시판 종류 조회
    public List<PostDto> getBoardInfoBoardIdAndBoardTypeSearch(Long boardId, String boardType) {
        return adminRepository.boardInfoBoardIdAndBoardTypeSearch(boardId,boardType);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 번호, 게시판 종류 조회
    public List<PostDto> getBoardInfoAllSearch(Long boardId, String boardType, String boardName) {
        return adminRepository.boardInfoAllSearch(boardId,boardType,boardName);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 등록
    @Transactional
    public BoardInfo createBoard(PostDto postDto) {
        BoardInfo boardInfo = boardInfoRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("게시판 정보를 찾을 수 없습니다"));
        postDto.setBoardInfo(boardInfo);
        return boardInfoRepository.save(postDto.boardInfoToEntity());
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 수정
    @Transactional
    public BoardInfo updateBoard(PostDto postDto) {
        // 1. 게시물 ID를 사용하여 해당 게시물을 조회합니다.
        BoardInfo boardInfo = boardInfoRepository.findById(postDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        // 2. 수정할 필드를 업데이트합니다.
        boardInfo.setBoardName(postDto.getBoardName());
        boardInfo.setBoardType(postDto.getBoardType());
        // 3. 수정된 엔티티를 저장합니다.
        return boardInfoRepository.save(boardInfo);
    }

    //관리자 - 게시판 정보관리 : 게시판 정보 목록 삭제
    @Transactional
    public void deleteBoard(Long boardId) {
        BoardInfo boardInfo = boardInfoRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시판 번호가 없습니다."));
        boardInfoRepository.delete(boardInfo);
    }

    //관리자 - 온라인강의수강현황 : 학습강좌 조회
    public List<LectDto> getFindLearningCourse() {
        return lectInfoRepository.findLearningCourse();
    }

    //관리자 - 온라인강의수강현황 : 학습강좌 강좌명으로 검색
    public List<LectDto> getLearningCourseLectNameSearch(String lectName) {
        return lectInfoRepository.learningCourseLectNameSearch(lectName);
    }

    // 관리자 - 온라인강의수강현황 : 학습강좌 강좌진행상태로 검색
    public List<LectDto> getLearningCourseIsActiveSearch(Boolean isActive) {
        return lectInfoRepository.learningCourseIsActiveSearch(isActive);
    }

    // 관리자 - 온라인강의수강현황 : 학습강좌 강좌명, 강좌진행상태로 검색
    public List<LectDto> getLearningCourseAllSearch(String lectName, Boolean isActive) {
        return lectInfoRepository.learningCourseAllSearch(lectName, isActive);
    }

    // 관리자 - 온라인강의수강현황 : 전체이수현황 조회
    public List<LectDto> getTotalCompletionStatus(Long lectId) {
        return lectInfoRepository.totalCompletionStatus(lectId);
    }


}
