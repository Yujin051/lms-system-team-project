package org.example.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class EnteringGradeDto {
    // 성적 ID
    private Long gradeId;
    // 과제 배점
    private Long lectAssign;
    // 출석 배점
    private Long lectCheck;
    // 시험 배점
    private Long lectTest;
    // 회원 이름
    private String userName;
    // 회원 이메일
    private String userEmail;
    //평가등급
    private String grade;
    //출석점수
    private Long checkScore;
    //과제점수
    private Long assignScore;
    //시험점수
    private Long testScore;

    public EnteringGradeDto(Long lectAssign, Long lectCheck, Long lectTest, String userName, String userEmail, String grade, Long checkScore, Long assignScore, Long testScore) {
        this.lectAssign = lectAssign;
        this.lectTest = lectTest;
        this.lectCheck = lectCheck;
        this.userName = userName;
        this.userEmail = userEmail;
        this.grade = grade;
        this.checkScore = checkScore;
        this.assignScore = assignScore;
        this.testScore =testScore;
    }
}