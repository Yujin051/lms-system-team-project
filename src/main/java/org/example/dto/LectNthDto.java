package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.LectInfo;
import org.example.entity.LectNth;
import org.example.entity.LmsConts;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectNthDto {

    /* lectInfo 테이블 컬럼 */
    private LectInfo lectInfo;
    private Long lectId;
    private String lectName;
    private String lectSubject;
    private LocalDateTime enrollStart;
    private LocalDateTime enrollEnd;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private boolean isActive;

    /* lectNth 테이블 컬럼 */
    private Long nthId;
    private Long nthSequence; //v
    private String nthName; //v

    /* lmsConts 테이블 컬럼 */
    private Long contsNo;
    private int contsTime; //v
    private String contsName;
    private String contsYout;
    private String contsDetail;


    /* 테이블 */
    private LectNth lectNth;
    private LmsConts lmsConts;


    // 온라인강의 조회
    public LectNthDto(Long lectId, String lectName, String lectSubject,
                      LocalDateTime lectStart, LocalDateTime lectEnd,
                      boolean isActive) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectSubject = lectSubject;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.isActive = isActive;
    }

    // 해당 lectId에 대한 강의 차시정보 조회


    public LectNthDto(Long lectId, Long nthId, Long nthSequence,
                      String nthName, Long contsNo, int contsTime) {
        this.lectId = lectId;
        this.nthId = nthId;
        this.nthSequence = nthSequence;
        this.nthName = nthName;
        this.contsNo = contsNo;
        this.contsTime = contsTime;
    }

    public LectNthDto(Long lectId, String lectName, String lectSubject,
                      LocalDateTime enrollStart, LocalDateTime enrollEnd,
                      LocalDateTime lectStart, LocalDateTime lectEnd,
                      boolean isActive, Long nthSequence,
                      String nthName, int contsTime, Long contsNo,
                      Long nthId, String contsName, String contsYout,
                      String contsDetail) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectSubject = lectSubject;
        this.enrollStart = enrollStart;
        this.enrollEnd = enrollEnd;
        this.isActive = isActive;
        this.nthSequence = nthSequence;
        this.nthName = nthName;
        this.nthId = nthId;
        this.contsName = contsName;
        this.contsYout = contsYout;
        this.contsDetail = contsDetail;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.contsTime = contsTime;
        this.contsNo = contsNo;
    }


    public LectNthDto(
            Long lectId,
            String lectName,
            String lectSubject,
            LocalDateTime enrollStart,
            LocalDateTime enrollEnd,
            LocalDateTime lectStart,
            LocalDateTime lectEnd,
            boolean isActive,
            Long nthSequence,
            String nthName,
            int contsTime
    ) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectSubject = lectSubject;
        this.enrollStart = enrollStart;
        this.enrollEnd = enrollEnd;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.isActive = isActive;
        this.nthSequence = nthSequence;
        this.nthName = nthName;
        this.contsTime = contsTime;
    }

    public LectNth toEntity() {
        return LectNth.builder()
                .lmsConts(lmsConts)
                .lectInfo(lectInfo)
                .nthSequence(nthSequence)
                .nthName(nthName)
                .build();
    }


    /* grid2 와 tb1 테이블 Respository 쓸 때 사용 */
    public LectNthDto(Long nthId, Long nthSequence,
                      String nthName, int contsTime) {

        this.nthId = nthId;
        this.nthSequence = nthSequence;
        this.nthName = nthName;
        this.contsTime = contsTime;
    }


}