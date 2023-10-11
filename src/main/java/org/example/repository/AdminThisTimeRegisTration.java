package org.example.repository;

import org.example.dto.LectNthDto;
import org.example.entity.LectNth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/* 온라인 강의수강현황 LectNth*/
public interface AdminThisTimeRegisTration extends JpaRepository<LectNth, Long> {

    /* 검색 조회기능 */
    @Query("select new org.example.dto.LectNthDto(lif.lectId, lif.lectName, lif.lectSubject, lif.enrollStart, lif.enrollEnd, "
            + "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthSequence, nth.nthName ,cs.contsTime) "
            + "from LectNth nth join nth.lectInfo lif " +
            "join nth.lmsConts cs " +
            "WHERE lif.lectName LIKE %:lectName% AND lif.isActive = :isActive")
    List<LectNthDto> findLectNthSearch(@Param("lectName") String lectName, @Param("isActive") Boolean isActive);

    /* 체크박스 검색 */
    @Query("SELECT NEW org.example.dto.LectNthDto(lif.lectId, lif.lectName, lif.lectSem, lif.enrollStart, lif.enrollEnd," +
           "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthSequence, nth.nthName ,cs.contsTime) "
            + "from LectNth nth join nth.lectInfo lif " +
            "join nth.lmsConts cs " +
           "WHERE lif.isActive = :isActive")
    List<LectNthDto> findLectNthBox(@Param("isActive") Boolean isActive);

    /* 온라인강좌명 강좌 검색 */
    @Query("select new org.example.dto.LectNthDto(lif.lectId, lif.lectName, lif.lectSubject, lif.enrollStart, lif.enrollEnd, "
            + "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthSequence, nth.nthName, cs.contsTime) "
            + "from LectNth nth join nth.lectInfo lif " +
            "join nth.lmsConts cs " +
            "WHERE lif.lectName like %:lectName%")
    List<LectNthDto> findLectName(@Param("lectName") String lectName);




    // 온라인강좌명, 강의운영상태를 검색
    @Query("SELECT NEW org.example.dto.LectNthDto(lif.lectId, lif.lectName, lif.lectSem, lif.enrollStart, lif.enrollEnd," +
            "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthSequence, nth.nthName, cs.contsTime) " +
            "FROM LectNth nth join nth.lectInfo lif " +
            "join nth.lmsConts cs")
    List<LectNthDto> findLectNthDtos(@Param("searchType") String searchType, @Param("nthKeyword") Boolean nthKeyword);

     /*검색 기능 */
    List<LectNth> findByNthIdContaining(String keyword);


    /* 강의 차시정보 하단테이블 비동기 처리*/
    @Query("SELECT NEW org.example.dto.LectNthDto(lif.lectId, lif.lectName, lif.lectSem, lif.enrollStart, lif.enrollEnd," +
            "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthSequence, nth.nthName, cs.contsTime) " +
            "FROM LectNth nth join nth.lectInfo lif " +
            "join nth.lmsConts cs")
    List<LectNthDto> findLectNthInfo(@Param("nthName") String nthName);
    }