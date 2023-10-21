package org.example.repository.admin;

import org.example.dto.admin.MemberDto;
import org.example.dto.admin.PostDto;
import org.example.dto.admin.StudentDto;
import org.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Member, Long> {

    // 관리자 : 학생관리 : 학생정보
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(m.userPhoneNum, m.userName, m.userBirthday, m.userGender, m.userEmail," +
            "m.userId, m.userAddr, s.studId, s.studNowCr) " +
            "FROM Student s JOIN s.member m ON s.member.id = m.id")
    List<MemberDto> findMembersWithDto();

    // 관리자 :
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, m.userName, m.userBirthday, m.userGender, m.userPhoneNum," +
            " m.userAddr, m.userEmail, s.studCreCpl, m.userId, m.id) " +
            "FROM Student s JOIN s.member m")
    List<MemberDto> findMemberDtoOne();

    // 관리자 - 학생관리 : 평균 학점
    @Query("SELECT NEW org.example.dto.admin.MemberDto(ROUND(AVG(s.studCreCpl), 2)) " +
            "FROM Student s JOIN s.member m")
    MemberDto findStudCreCplAvg();


    //관리자 - 학생관리 : 이름검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studNowCr, s.studId, m.userName, m.userBirthday, m.userGender, m.userPhoneNum, m.userEmail, " +
            "m.userAddr, m.userId) " +
            "FROM Student s JOIN s.member m where m.userName LIKE %:keyword%")
    List<MemberDto> findByUserNameContainingIgnoreCase(@Param("keyword") String keyword);

    //관리자 - 학생관리 : 학번검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studNowCr, s.studId, m.userName, m.userBirthday, m.userGender, m.userPhoneNum, m.userEmail, " +
            "m.userAddr, m.userId) " +
            "FROM Student s JOIN s.member m " +
            "where m.userId LIKE %:keyword%")
    List<MemberDto> findByStudId(@Param("keyword") String keyword);

    //관리자 - 학생관리 : 이름, 학번 전체 검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studNowCr, s.studId, m.userName, m.userBirthday, m.userGender, m.userPhoneNum, m.userEmail, " +
            "m.userAddr, m.userId) " +
            "FROM Student s JOIN s.member m " +
            "WHERE (:idKeyword IS NULL OR m.userId LIKE CONCAT('%', :idKeyword, '%')) " +
            "AND (:nameKeyword IS NULL OR m.userName LIKE CONCAT('%', :nameKeyword, '%'))")
    List<MemberDto> findByStudIdAndUserName(@Param("idKeyword") String idKeyword, @Param("nameKeyword") String nameKeyword);

    //관리자 - 학생관리 : 검색어가 비어있을때 조회버튼 누르면 전체 조회
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studNowCr, s.studId, m.userName, m.userBirthday, m.userGender, m.userPhoneNum, m.userEmail, " +
            "m.userAddr, m.userId) " +
            "FROM Student s JOIN s.member m " +
            "WHERE (:nameKeyword IS NULL OR m.userName LIKE CONCAT('%', :nameKeyword, '%')) " +
            "AND (:idKeyword IS NULL OR m.userId LIKE CONCAT('%', :idKeyword, '%'))")
    List<MemberDto> noSearchByNameAndStudId(
            @Param("nameKeyword") String nameKeyword,
            @Param("idKeyword") String idKeyword);


    // 관리자 : 전체성적관리 : 학생정보조회
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studGrade, m.userName, m.userBirthday, m.userGender, m.userEmail," +
            " m.userId, m.id, s.studId) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id")
    List<MemberDto> findGradeStudInfo();

    // 관리자 - 전체성적관리 : 이름검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studGrade, m.userName, m.userBirthday, m.userGender, m.userEmail," +
            " m.userId, m.id, s.studId) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "WHERE m.userName LIKE %:keyword%")
    List<MemberDto> gradeFindByUserNameContainingIgnoreCase(@Param("keyword") String keyword);

    // 관리자 - 전체성적관리 : 학번검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studGrade, m.userName, m.userBirthday, m.userGender, m.userEmail," +
            " m.userId, m.id, s.studId) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "WHERE m.userId LIKE %:keyword%")
    List<MemberDto> gradeFindByStudId(@Param("keyword") String keyword);

    // 관리자 - 전체성적관리 : 학년검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studGrade, m.userName, m.userBirthday, m.userGender, m.userEmail," +
            " m.userId, m.id, s.studId) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "WHERE s.studGrade = :keyword")
    List<MemberDto> gradeFindByStudGrade(@Param("keyword") Long keyword);

    // 관리자 - 전체성적관리 : 이름, 학번 검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studGrade, m.userName, m.userBirthday, " +
            "m.userGender, m.userEmail," +
            " m.userId, m.id, s.studId)" +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "WHERE (m.userId LIKE CONCAT('%', :idKeyword, '%') OR :idKeyword IS NULL) " +
            "AND   (m.userName LIKE CONCAT('%', :nameKeyword, '%') OR :nameKeyword IS NULL) ")
    List<MemberDto> gradeFindUserIdAndUserName(@Param("idKeyword") String idKeyword,
                                               @Param("nameKeyword") String nameKeyword);


    //관리자 - 전체성적관리 : 이름, 학번, 학년 전체 검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studGrade, m.userName, m.userBirthday, m.userGender, m.userEmail," +
            " m.userId, m.id, s.studId) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "WHERE (m.userId LIKE CONCAT('%', :idKeyword, '%') OR :idKeyword IS NULL) " +
            "AND (m.userName LIKE CONCAT('%', :nameKeyword, '%') OR :nameKeyword IS NULL) " +
            "AND (s.studGrade = :gradeKeyword OR :gradeKeyword IS NULL)")
    List<MemberDto> gradeFindAllSearch(@Param("idKeyword") String idKeyword,
                                       @Param("nameKeyword") String nameKeyword,
                                       @Param("gradeKeyword") Long gradeKeyword);

    //관리자 - 전체성적관리 : 검색어가 비어있을 때 전체 조회되게
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studGrade, m.userName, m.userBirthday, m.userGender, m.userEmail," +
            " m.userId, m.id, s.studId) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "WHERE (m.userId LIKE CONCAT('%', :idKeyword, '%') OR :idKeyword IS NULL) " +
            "AND (m.userName LIKE CONCAT('%', :nameKeyword, '%') OR :nameKeyword IS NULL) " +
            "AND (s.studGrade = :gradeKeyword OR :gradeKeyword IS NULL)")
    List<MemberDto> gradeNoSearch(
            @Param("nameKeyword") String nameKeyword,
            @Param("idKeyword") String idKeyword,
            @Param("gradeKeyword") Long gradeKeyword);

    // 관리자 - 전체성적관리 : 전체 학기성적 및 전체 현황
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(m.userId, m.id, sg.semSem, s.studNowCr, s.studMaxCr," +
            "s.studId) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "JOIN SemGrade sg ON s.studId = sg.student.studId " +
            "WHERE s.studId = :studId")
    List<MemberDto> allGradeRecord(@Param("studId") Long studId);

    // 관리자 - 전체성적관리 : 강좌별성적
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(li.lectName, gi.grade, gi.checkScore, gi.assignScore, gi.testScore) " +
            "FROM Student s JOIN s.member m ON m.id = s.member.id " +
            "JOIN StudLectApply ap ON ap.student.studId = s.studId " +
            "JOIN LectInfo li ON li.lectId = ap.lectInfo.lectId " +
            "JOIN GradeInfo gi ON gi.studLectApply.applyId = ap.applyId " +
            "WHERE s.studId = :studId")
    List<MemberDto> findGradesByCourse(@Param("studId") Long studId);

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 조회
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id")
    List<PostDto> postInfo();

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판종류 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id " +
            "WHERE bi.boardType = :keyword")
    List<PostDto> articleFindByUserNameContainingIgnoreCase(@Param("keyword") String keyword);

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 검색조건(제목, 내용, 작성자) 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id " +
            "WHERE " +
            "   ((:searchType = '제목' AND ba.articleTitle LIKE %:keyword% ) OR " +
            "   (:searchType = '내용' AND ba.articleContent LIKE %:keyword% ) OR " +
            "   (:searchType = '작성자' AND m.userName LIKE %:keyword% )" +
            ")")
    List<PostDto> articleSearchRequirement(@Param("keyword") String keyword,
                                           @Param("searchType") String searchType);

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 작성일 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            " ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id " +
            "WHERE ba.articleAt = :keyword")
    List<PostDto> postSearchArticleAt(@Param("keyword") LocalDate keyword);

    // 관리자 - 게시글 작성(담당용) : 게시글 목록 게시판종류, 작성일 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id " +
            "WHERE bi.boardType = :boardTypeKeyword " +
            "AND ((:searchType = '제목' AND ba.articleTitle LIKE %:requirement%) OR " +
            "     (:searchType = '내용' AND ba.articleContent LIKE %:requirement%) OR " +
            "     (:searchType = '작성자' AND m.userName LIKE %:requirement%))")
    List<PostDto> postSearchBoardTypeAndRequirement(@Param("boardTypeKeyword") String boardTypeKeyword,
                                                    @Param("searchType") String searchType,
                                                    @Param("requirement") String requirement);

    // 관리자 - 게시글 작성(담당용) : 게시판종류, 검색조건, 작성일 모두 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id " +
            "WHERE (bi.boardType = :boardTypeKeyword) " +
            "AND (" +
            "   (:searchType = '제목' AND ba.articleTitle LIKE %:requirement%) OR " +
            "   (:searchType = '내용' AND ba.articleContent LIKE %:requirement%) OR " +
            "   (:searchType = '작성자' AND m.userName LIKE %:requirement%)" +
            ") " +
            "AND (ba.articleAt = :articleAt)")
    List<PostDto> postAllSearch(@Param("boardTypeKeyword") String boardTypeKeyword,
                                @Param("searchType") String searchType,
                                @Param("requirement") String requirement,
                                @Param("articleAt") LocalDate articleAt);

    // 관리자 - 게시글 작성(담당용) : 게시판종류, 검색조건, 작성일 비어있을때 전체 조회
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id " +
            "WHERE " +
            "   (:boardTypeKeyword IS NULL OR bi.boardType = :boardTypeKeyword) " +
            "   AND (:searchType IS NULL OR (" +
            "       (:searchType = '제목' AND ba.articleTitle LIKE %:requirement%) OR " +
            "       (:searchType = '내용' AND ba.articleContent LIKE %:requirement%) OR " +
            "       (:searchType = '작성자' AND m.userName LIKE %:requirement%)" +
            "   )) " +
            "   AND (:articleAt IS NULL OR ba.articleAt = :articleAt)")
    List<PostDto> postNoSearch(@Param("boardTypeKeyword") String boardTypeKeyword,
                               @Param("searchType") String searchType,
                               @Param("requirement") String requirement,
                               @Param("articleAt") LocalDate articleAt);

    // 관리자 - 게시글 작성(담당용) : 해당 게시글 번호의 게시글 내용 조회
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(m.userName, ba.Id, ba.articleTitle, ba.articleAt, ba.articleView, " +
            "ba.isLocked, ba.isDeleted, ba.articleContent, bi.boardType, bi.Id) " +
            "FROM Member m " +
            "JOIN BoardArticle ba ON ba.memberId.id = m.id " +
            "JOIN BoardInfo bi ON bi.Id = ba.boardInfo.Id " +
            "WHERE ba.Id = :articleId")
    List<PostDto> postContents(@Param("articleId") Long articleId);

    // 관리자 - 게시판 정보관리 : 게시판 정보 목록 조회
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(bi.Id, bi.boardType, bi.boardName) " +
            "FROM BoardInfo bi")
    List<PostDto> boardInfo();

    // 관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 번호 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(bi.Id, bi.boardType, bi.boardName) " +
            "FROM BoardInfo bi " +
            "WHERE bi.Id = :boardId")
    List<PostDto> boardInfoBoardIdSearch(@Param("boardId") Long boardId);

    // 관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 이름 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(bi.Id, bi.boardType, bi.boardName) " +
            "FROM BoardInfo bi " +
            "WHERE bi.boardName LIKE %:boardName%")
    List<PostDto> boardInfoBoardNameSearch(@Param("boardName") String boardName);

    // 관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 종류 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(bi.Id, bi.boardType, bi.boardName) " +
            "FROM BoardInfo bi " +
            "WHERE bi.boardType = :boardType")
    List<PostDto> boardInfoBoardTypeSearch(@Param("boardType") String boardType);

    // 관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 번호, 게시판 이름 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(bi.Id, bi.boardType, bi.boardName) " +
            "FROM BoardInfo bi " +
            "WHERE bi.Id = :boardId " +
            "AND bi.boardName LIKE %:boardName%")
    List<PostDto> boardInfoBoardIdAndBoardNameSearch(@Param("boardId") Long boardId,
                                                     @Param("boardName") String boardName);

    // 관리자 - 게시판 정보관리 : 게시판 정보 목록 게시판 번호, 게시판 이름 검색
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(bi.Id, bi.boardType, bi.boardName) " +
            "FROM BoardInfo bi " +
            "WHERE bi.Id = :boardId " +
            "AND bi.boardType = :boardType")
    List<PostDto> boardInfoBoardIdAndBoardTypeSearch(@Param("boardId") Long boardId,
                                                     @Param("boardType") String boardType);

    // 관리자 - 게시판 정보관리 : 게시판 정보 목록 모든 검색어 조회
    @Query("SELECT NEW org.example.dto.admin.PostDto" +
            "(bi.Id, bi.boardType, bi.boardName) " +
            "FROM BoardInfo bi " +
            "WHERE bi.Id = :boardId " +
            "AND bi.boardType = :boardType " +
            "AND bi.boardName LIKE %:boardName%")
    List<PostDto> boardInfoAllSearch(@Param("boardId") Long boardId,
                                     @Param("boardType") String boardType,
                                     @Param("boardName") String boardName);

}
