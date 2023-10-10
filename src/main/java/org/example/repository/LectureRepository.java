package org.example.repository;

import org.example.dto.admin.LectureListDto;
import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface LectureRepository extends JpaRepository<LectInfo, Long> {

    // 강좌 관리 페이지에 필요한 정보 모두 찾는 쿼리
    @Query("SELECT new org.example.dto.admin.LectureListDto(l.isActive, l.lectName, l.lectElem, " +
            "l.lectCredit, l.lectSubject, l.lectId, l.lectStart, l.lectEnd, p.member.userName) from LectInfo l" +
            " JOIN Professor p ON l.professor.id = p.id")
    List<LectureListDto> findAllLectList();

    // 상세정보 불러오는 쿼리
    @Query("SELECT new org.example.dto.admin.LectureListDto(l.isActive, l.lectName, l.lectElem, " +
            "l.lectCredit, l.lectSubject, l.lectId, l.lectStart, l.lectEnd, p.member.userName) from LectInfo l" +
            " JOIN Professor p ON l.professor.id = p.id WHERE l.lectName = :lectName")
    LectureListDto findLecture(@Param("lectName") String lectName);

    // 검색 조건을 포함하여 리스트 출력하는 쿼리
    @Query("SELECT new org.example.dto.admin.LectureListDto(l.isActive, l.lectName, l.lectElem, " +
            "l.lectCredit, l.lectSubject, l.lectId, l.lectStart, l.lectEnd, p.member.userName) from LectInfo l" +
            " JOIN Professor p ON l.professor.id = p.id WHERE " +
            "l.lectYear LIKE %:year% AND " +
            "l.lectSem LIKE %:sem% AND " +
            "l.isActive = :active AND " +
            "l.lectSubject LIKE %:subject% AND " +
            "l.lectName LIKE %:name% AND l.lectElem = :elem")
    List<LectureListDto> findLectList(@Param("year") String year, @Param("sem") String sem,
                                         @Param("active") boolean active, @Param("subject") String subject,
                                         @Param("name") String name, @Param("elem") int elem);


}
