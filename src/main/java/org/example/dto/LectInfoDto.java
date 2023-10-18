package org.example.dto;

import lombok.Data;
import org.example.entity.LectInfo;

import java.time.LocalDateTime;

@Data
public class LectInfoDto {
    /* lectInfo 테이블 Dto*/
    private LectInfo lectInfo;
    private Long lectId;
    private String lectName;
    private String lectSubject;
    private LocalDateTime enrollStart;
    private LocalDateTime enrollEnd;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private boolean isActive;

    // 온라인 강의 조회
    public LectInfoDto(Long lectId, String lectName, String lectSubject,
                       LocalDateTime enrollStart, LocalDateTime enrollEnd,
                       boolean isActive) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectSubject = lectSubject;
        this.enrollStart = enrollStart;
        this.enrollEnd = enrollEnd;
        this.isActive = isActive;
    }
}
