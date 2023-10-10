package org.example.repository.admin;

import org.example.dto.admin.MemberDto;
import org.example.dto.admin.StudentDto;
import org.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Member, Long> {

    // 관리자 : 학생정보
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, m.userName, m.userBirthday, m.userPhoneNum, m.userEmail) " +
            "FROM Student s JOIN s.member m")
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
            "(s.studId, m.userName, m.userBirthday, m.userPhoneNum, m.userEmail) " +
            "FROM Student s JOIN s.member m where m.userName LIKE %:keyword%")
    List<MemberDto> findByUserNameContainingIgnoreCase(@Param("keyword") String keyword);

    //관리자 - 학생관리 : 학번검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, m.userName, m.userBirthday, m.userPhoneNum, m.userEmail) " +
            "FROM Student s JOIN s.member m where s.studId = :keyword")
    List<MemberDto> findByStudId(@Param("keyword") Long keyword);

    //관리자 - 학생관리 : 이름, 학번 전체 검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, m.userName, m.userBirthday, m.userPhoneNum, m.userEmail) " +
            "FROM Student s JOIN s.member m where s.studId = :idKeyword and m.userName Like %:nameKeyword%")
    List<MemberDto> findByStudIdAndUserName(@Param("idKeyword") Long idKeyword, @Param("nameKeyword") String nameKeyword);

    //관리자 - 학생관리 : 검색어가 비어있을때 조회버튼 누르면 전체 조회
    @Query("SELECT NEW org.example.dto.admin.MemberDto(s.studId, m.userName, m.userBirthday, m.userPhoneNum, m.userEmail) " +
            "FROM Student s JOIN s.member m " +
            "WHERE (:nameKeyword IS NULL OR m.userName LIKE %:nameKeyword%) " +
            "AND (:idKeyword IS NULL OR s.studId = :idKeyword)")
    List<MemberDto> noSearchByNameAndStudId(
            @Param("nameKeyword") String nameKeyword,
            @Param("idKeyword") Long idKeyword);

    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, m.userName, m.userBirthday, m.userGender, m.userPhoneNum," +
            " m.userAddr, m.userEmail, AVG(s.studCreCpl), m.userId, m.id) " +
            "FROM Student s JOIN s.member m WHERE m.id = :memberId")
    MemberDto findMemberInfo(@Param("memberId") Long memberId);

    // 관리자 : 전체성적관리 학생정보조회
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, s.studGrade, s.studNowCr, m.userName," +
            " m.userBirthday, m.userGender, m.userEmail, " +
            "m.userId,sg.semGrade, sg.semYear, sg.semSem) " +
            "FROM Student s JOIN s.member m JOIN s.semGrade sg")
    List<MemberDto> findGradeStudInfo();

    @Query("select s.studId FROM Student s Join s.member m JOIN s.semGrade sg where m.id = s.member.id"
            + " and m.userName = :name and s.studGrade = :grade")
    Long findStudentIdByNameAndGrade(@Param("name") String name,
                                     @Param("grade") String grade);

    // 관리자 - 전체성적관리 : 이름검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, s.studGrade, s.studNowCr, m.userName," +
            " m.userBirthday, m.userGender, m.userEmail, " +
            "m.userId,sg.semGrade, sg.semYear, sg.semSem) " +
            "FROM Student s JOIN s.member m JOIN s.semGrade sg where m.userName LIKE %:keyword%")
    List<MemberDto> gradeFindByUserNameContainingIgnoreCase(@Param("keyword") String keyword);

    // 관리자 - 전체성적관리 : 학번검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, s.studGrade, s.studNowCr, m.userName," +
            " m.userBirthday, m.userGender, m.userEmail, " +
            "m.userId,sg.semGrade, sg.semYear, sg.semSem) " +
            "FROM Student s JOIN s.member m JOIN s.semGrade sg where s.studId = :keyword")
    List<MemberDto> gradeFindByStudId(@Param("keyword") Long keyword);

    // 관리자 - 전체성적관리 : 학년검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, s.studGrade, s.studNowCr, m.userName," +
            " m.userBirthday, m.userGender, m.userEmail, " +
            "m.userId,sg.semGrade, sg.semYear, sg.semSem) " +
            "FROM Student s JOIN s.member m JOIN s.semGrade sg where s.studGrade = :keyword")
    List<MemberDto> gradeFindByStudGrade(@Param("keyword") Long keyword);

    //관리자 - 전체성적관리 : 이름, 학번, 학년 전체 검색
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, s.studGrade, s.studNowCr, m.userName," +
            " m.userBirthday, m.userGender, m.userEmail, " +
            "m.userId,sg.semGrade, sg.semYear, sg.semSem) " +
            "FROM Student s JOIN s.member m JOIN s.semGrade sg where s.studId = :idKeyword and m.userName Like %:nameKeyword% and s.studGrade = :gradeKeyword")
    List<MemberDto> gradeFindAllSearch(@Param("idKeyword") Long idKeyword,
                                            @Param("nameKeyword") String nameKeyword,
                                            @Param("gradeKeyword") Long gradeKeyword);

    //관리자 - 전체성적관리 : 검색어가 비어있을 때 전체 조회되게
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(s.studId, s.studGrade, s.studNowCr, m.userName," +
            " m.userBirthday, m.userGender, m.userEmail, " +
            "m.userId,sg.semGrade, sg.semYear, sg.semSem) " +
            "FROM Student s JOIN s.member m JOIN s.semGrade sg " +
            "WHERE (:nameKeyword IS NULL OR m.userName LIKE %:nameKeyword%) " +
            "AND (:idKeyword IS NULL OR s.studId = :idKeyword)")
    List<MemberDto> gradeNoSearch(
            @Param("nameKeyword") Long nameKeyword,
            @Param("idKeyword") String idKeyword);

    // 관리자 - 전체성적관리 : 강좌별성적
    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(l.lectId, l.lectName, l.lectStart, l.lectEnd, l.lectCredit, l.isActive," +
            "m.userName, s.studId) " +
            "FROM Member m JOIN Professor p ON m.professor.id = p.id " +
            "JOIN LectInfo l ON p.id = l.professor.id JOIN Student s ON s.studId = m.student.studId " +
            "WHERE s.studId = :studId")
    List<MemberDto> gradesByCourse(@Param("studId") Long studId);

}
