package org.example.dto.admin;

import java.time.LocalDateTime;

public class MemberDto {
    private Long lectId;
    private String lectName;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private Long lectCredit;
    private String isActive;
    private String userName;
    private Long studId;

    public MemberDto(Long lectId, String lectName, LocalDateTime lectStart, LocalDateTime lectEnd, Long lectCredit, String isActive, String userName, Long studId) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.lectCredit = lectCredit;
        this.isActive = isActive;
        this.userName = userName;
        this.studId = studId;
    }
}
