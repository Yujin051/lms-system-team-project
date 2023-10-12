package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.LmsConts;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectNthDto {


    private Long lectId;
    private String lectName;
    private String lectSubject;
    private LocalDateTime enrollStart;
    private LocalDateTime enrollEnd;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private boolean isActive;
    private Long nthSequence;
    private String nthName;
    private int contsTime;
    private Long contsNo;
    private Long nthId;
    private String contsName;
    private String contsYout;
    private String contsDetail;


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




}