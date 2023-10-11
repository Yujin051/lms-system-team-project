package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureListDto {
    private boolean isActive;
    private String lectName;
    private int lectElem;
    private Long lectCredit;
    private String lectSubject;
    private Long lectId;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private String userName;
    private LocalDateTime enrollStart;
    private LocalDateTime enrollEnd;
    private Long lectAssign;
    private Long lectCheck;
    private Long lectTest;
    private Long lectNowNum;
    private Long lectMaxNum;
    private String lectYear;
    private String lectSem;

    public LectureListDto(boolean isActive, String lectName, int lectElem, Long lectCredit, String lectSubject, Long lectId, LocalDateTime lectStart, LocalDateTime lectEnd, String userName) {
        this.isActive = isActive;
        this.lectName = lectName;
        this.lectElem = lectElem;
        this.lectCredit = lectCredit;
        this.lectSubject = lectSubject;
        this.lectId = lectId;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.userName = userName;
    }
}
