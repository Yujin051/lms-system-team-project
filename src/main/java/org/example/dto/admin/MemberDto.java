package org.example.dto.admin;

import lombok.*;
import org.example.constant.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    //student
    private Long studNowCr; //현재수강학점
    private Long studId; //학번
    private Long studGrade; //등급
    private double studCreCpl; //평균

    //member
    private String userName;
    private LocalDate userBirthday;
    private Gender userGender;
    private String userPhoneNum;
    private String userEmail;
    private String userAddr;
    private String userId;
    private Long id;

    //sem_grade
    private String semGrade; //등급
    private String semYear; //년도
    private String semSem; //학기

    //professor
    private Long profId;

    //lect_info
    private Long lectId;
    private String lectName;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private Long lectCredit;
    private boolean isActive;


    // 관리자 - 학생정보
    public MemberDto(Long studId, String userName, LocalDate userBirthday,
                     String userPhoneNum, String userEmail) {
        this.studId = studId;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
    }

    // 관리자 학점 평균
    public MemberDto(double studCreCpl) {
        this.studCreCpl = studCreCpl;
    }


    // 관리자 - 학생정보 : 기본정보
    public MemberDto(Long studId, String userName, LocalDate userBirthday,
                     Gender userGender, String userPhoneNum, String userEmail,
                     String userAddr, double studCreCpl, String userId, Long id) {
        this.studId = studId;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
        this.userAddr = userAddr;
        this.studCreCpl = studCreCpl;
        this.userId = userId;
        this.id = id;
    }

    // 관리자 - 전체성적관리 : 학생정보조회
    public MemberDto(Long studId, Long studGrade, Long studNowCr, String userName,
                     LocalDate userBirthday, Gender userGender, String userEmail,
                     String userId, String semGrade, String semYear, String semSem) {
        this.studId = studId;
        this.studGrade = studGrade;
        this.studNowCr = studNowCr;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userId = userId;
        this.semGrade = semGrade;
        this.semYear = semYear;
        this.semSem = semSem;
    }

    // 관리자 - 전체성적관리 : 학생정보
    public MemberDto(Long studId, Long studGrade, String userName,
                     LocalDate userBirthday, Gender userGender, String userEmail) {
        this.studId = studId;
        this.studGrade = studGrade;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userEmail = userEmail;
    }

    // 관리자 - 전체성적관리 : 강좌별성적
    public MemberDto(Long lectId, String lectName, LocalDateTime lectStart,
                     LocalDateTime lectEnd, Long lectCredit, boolean isActive, String userName,
                     Long studId) {
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
