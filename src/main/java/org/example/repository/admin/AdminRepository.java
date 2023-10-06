package org.example.repository.admin;

import org.example.dto.admin.MemberDto;
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
}
