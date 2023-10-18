package org.example.repository;

import org.example.dto.LectNthDto;
import org.example.dto.LmsContsDto;
import org.example.entity.LectNth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 온라인 강의수강현황 LectNth*/
@Repository
public interface LectNthRepository extends JpaRepository<LectNth, Long> {


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
            + "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthSequence, nth.nthName, cs.contsTime)"
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
    @Query("SELECT NEW org.example.dto.LectNthDto" +
            "(lif.lectId, nth.nthId, nth.nthSequence, nth.nthName,cs.contsNo, cs.contsTime) " +
            "FROM LectNth nth " +
            "JOIN LectInfo lif on lif.lectId = nth.lectInfo.lectId " +
            "JOIN LmsConts cs ON cs.contsNo = nth.lmsConts.contsNo" +
            " where lif.lectId = :lectId")
    List<LectNthDto> findLectIdInfo(@Param("lectId") Long lectId);


    // grid2 조회
    @Query("SELECT NEW org.example.dto.LectNthDto" +
            "(lif.lectId, lif.lectName, lif.lectSubject, lif.enrollStart, lif.enrollEnd," +
            "lif.lectStart, lif.lectEnd, lif.isActive, nth.nthSequence, nth.nthName, cs.contsTime, cs.contsNo, " +
            "nth.nthId, cs.contsName, cs.contsYout, cs.contsDetail) " +
            "FROM LectNth nth" +
            " join LectInfo lif on lif.lectId = nth.lectInfo.lectId" +
            " join LmsConts cs on nth.lmsConts.contsNo = cs.contsNo")
    List<LectNthDto> findLectInfo();


    /* 강의 차시정보 신규/저장 */
    @Query("SELECT NEW org.example.dto.LmsContsDto(nth.nthId, cs.contsNo, nth.nthSequence," +
            "cs.contsName, nth.nthName, cs.contsYout, cs.contsTime) " +
            "FROM LectNth nth " +
            " join nth.lectInfo lif on lif.lectId = nth.lectInfo.lectId" +
            " join nth.lmsConts cs on nth.lmsConts.contsNo = cs.contsNo" +
            " where nth.nthId = :nthId")
    // conts 를 nthId로바꿈
    List<LmsContsDto> findContsNo(@Param("nthId") Long nthId);

}

