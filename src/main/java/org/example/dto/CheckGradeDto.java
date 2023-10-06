package org.example.dto;

import lombok.Getter;

@Getter
public class CheckGradeDto {
    private Long lectId;
    private String lectName;
    private Long lectCredit;
    private String userName;
    private String grade;

    public CheckGradeDto(Long lectId, String lectName, Long lectCredit, String userName, String grade) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectCredit = lectCredit;
        this.userName = userName;
        this.grade = grade;
    }
}
