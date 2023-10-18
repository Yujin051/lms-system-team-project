package org.example.repository.admin;

import org.example.dto.LectNthDto;
import org.example.dto.admin.LectDto;
import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectInfoRepository extends JpaRepository<LectInfo, Long> {

    // 관리자 - 온라인강의수강현황 : 학습강좌 조회
    @Query("SELECT NEW org.example.dto.admin.LectDto" +
            "(li.lectId, li.lectName, li.lectSubject, li.lectStart, li.lectEnd, " +
            "li.isActive) " +
            "FROM LectInfo li")
    List<LectDto> findLearningCourse();

    // 관리자 - 온라인강의수강현황 : 학습강좌 강좌명으로 검색
    @Query("SELECT NEW org.example.dto.admin.LectDto" +
            "(li.lectId, li.lectName, li.lectSubject, li.lectStart, li.lectEnd, " +
            "li.isActive) " +
            "FROM LectInfo li " +
            "WHERE li.lectName LIKE %:lectName%")
    List<LectDto> learningCourseLectNameSearch(@Param("lectName") String lectName);

    // 관리자 - 온라인강의수강현황 : 학습강좌 강좌진행상태로 검색
    @Query("SELECT NEW org.example.dto.admin.LectDto" +
            "(li.lectId, li.lectName, li.lectSubject, li.lectStart, li.lectEnd, " +
            "li.isActive) " +
            "FROM LectInfo li " +
            "WHERE li.isActive = :isActive")
    List<LectDto> learningCourseIsActiveSearch(@Param("isActive") Boolean isActive);

    // 관리자 - 온라인강의수강현황 : 학습강좌 강좌명, 강좌진행상태로 검색
    @Query("SELECT NEW org.example.dto.admin.LectDto" +
            "(li.lectId, li.lectName, li.lectSubject, li.lectStart, li.lectEnd, " +
            "li.isActive) " +
            "FROM LectInfo li " +
            "WHERE li.lectName LIKE %:lectName% " +
            "AND li.isActive = :isActive")
    List<LectDto> learningCourseAllSearch(@Param("lectName") String lectName,
                                          @Param("isActive") Boolean isActive);

    // 관리자 - 온라인강의수강현황 : 전체이수현황 조회
    @Query("SELECT NEW org.example.dto.admin.LectDto" +
            "(li.lectName, li.lectCredit, s.studGrade, m.userName, m.userId) " +
            "FROM Member m JOIN Student s ON m.id = s.member.id " +
            "JOIN StudLectApply ap ON s.studId = ap.student.studId " +
            "JOIN LectInfo li ON ap.lectInfo.lectId = li.lectId " +
            "WHERE li.lectId = :lectId")
    List<LectDto> totalCompletionStatus(@Param("lectId") Long lectId);

    @Query("SELECT NEW org.example.dto.LectNthDto " +
            "(lif.lectId, lif.lectName, lif.lectSubject, lif.lectStart, lif.lectEnd, lif.isActive) " +
            "FROM LectInfo lif")
    List<LectNthDto> findLectInfo2();
}
