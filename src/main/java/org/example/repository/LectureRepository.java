package org.example.repository;

import org.example.dto.admin.LectureListDto;
import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LectureRepository extends JpaRepository<LectInfo, Long> {

    // 강좌 관리 페이지에 필요한 정보 모두 찾는 쿼리
    @Query("SELECT new org.example.dto.admin.LectureListDto(l.isActive, l.lectName, l.lectElem, " +
            "l.lectCredit, l.lectSubject, l.lectId, l.lectStart, l.lectEnd, p.member.userName) from LectInfo l" +
            " JOIN Professor p ON l.professor.id = p.id")
    List<LectureListDto> findAllLectList();

    // 상세정보 불러오는 쿼리
    @Query("SELECT new org.example.dto.admin.LectureListDto(l.isActive, l.lectName, l.lectElem, " +
            "l.lectCredit, l.lectSubject, l.lectId, l.lectStart, l.lectEnd, p.member.userName, " +
            "l.enrollStart, l.enrollEnd, l.lectAssign, l.lectCheck, l.lectTest, l.lectNownum, " +
            "l.lectMaxnum, l.lectYear, l.lectSem)" +
            " from LectInfo l JOIN Professor p ON l.professor.id = p.id WHERE l.lectId = :lectId")
    LectureListDto findLecture(@Param("lectId") Long lectId);

    // 검색 조건을 포함하여 리스트 출력하는 쿼리
    @Query("SELECT new org.example.dto.admin.LectureListDto(l.isActive, l.lectName, l.lectElem, " +
            "l.lectCredit, l.lectSubject, l.lectId, l.lectStart, l.lectEnd, p.member.userName) " +
            "FROM LectInfo l JOIN Professor p ON l.professor.id = p.id " +
            "WHERE (:year IS NULL OR l.lectYear LIKE %:year%) AND " +
            "(:sem IS NULL OR l.lectSem LIKE %:sem%) AND " +
            "(:active IS NULL OR l.isActive = :active) AND " +
            "(:subject IS NULL OR l.lectSubject LIKE %:subject%) AND " +
            "(:name IS NULL OR l.lectName LIKE %:name%) AND " +
            "l.lectElem = :elem")
    List<LectureListDto> findLectList(@Param("year") String year, @Param("sem") String sem,
                                         @Param("active") boolean active, @Param("subject") String subject,
                                         @Param("name") String name, @Param("elem") int elem);

    // 수강신청에서 사용하는 검색(학년, 학기, 이름)
    @Query("SELECT new org.example.dto.admin.LectureListDto(l.isActive, l.lectName, l.lectElem, " +
            "l.lectCredit, l.lectSubject, l.lectId, l.lectStart, l.lectEnd, p.member.userName) from LectInfo l" +
            " JOIN Professor p ON l.professor.id = p.id WHERE " +
            "l.lectYear LIKE %:year% AND " +
            "l.lectSem LIKE %:sem% AND " +
            "l.lectName LIKE %:name%")
    List<LectureListDto> findApplyLectList(@Param("year") String year, @Param("sem") String sem,
                                           @Param("name") String name);

}
