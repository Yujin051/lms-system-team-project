package org.example.dto.admin;

import lombok.*;
import org.example.constant.Gender;
import org.example.constant.RoleType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    //student
    private Long studNowCr; //현재수강학점
    private Long studMaxCr; //최대수강학점
    private Long studId; //학번
    private Long studGrade; //학년
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
    private RoleType userRole;
    private String imgSaved;

    //sem_grade
    private String semRating; //등급
    private String semYear; //년도
    private String semSem; //학기

    //professor
    private Long profId;

    //lect_info
    private Long lectId;
    private String lectName;
    private LocalDate lectStart;
    private LocalDate lectEnd;
    private Long lectCredit;
    private boolean isActive;

    //grade_info
    private String grade;
    private Long checkScore;
    private Long assignScore;
    private Long testScore;
    private Boolean isRecord;

    //학생정보 : 기본정보
    public MemberDto(String userPhoneNum, String userName, LocalDate userBirthday,
                     Gender userGender, String userEmail, String userId,
                     String userAddr, Long studId, Long studNowCr, String imgSaved) {
        this.userPhoneNum = userPhoneNum;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userAddr = userAddr;
        this.studId = studId;
        this.studNowCr = studNowCr;
        this.imgSaved = imgSaved;
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

    public MemberDto(Long studGrade, String userName, LocalDate userBirthday,
                     Gender userGender, String userEmail, String userId, Long id,
                     Long studId) {
        this.studGrade = studGrade;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userId = userId;
        this.id = id;
        this.studId = studId;
    }

    // 관리자 - 전체성적관리 : 학생정보조회
    public MemberDto(Long studGrade, String userName, LocalDate userBirthday,
                     Gender userGender, String userEmail, String userId, Long id,
                     Long studId, String imgSaved) {
        this.studGrade = studGrade;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userId = userId;
        this.id = id;
        this.studId = studId;
        this.imgSaved = imgSaved;
    }

    // 관리자 - 전체성적관리 : 전체 학기성적 및 전체 현황

    public MemberDto(String userId, Long id, String semSem,
                     Long studNowCr, Long studMaxCr, Long studId) {
        this.userId = userId;
        this.id = id;
        this.semSem = semSem;
        this.studNowCr = studNowCr;
        this.studMaxCr = studMaxCr;
        this.studId = studId;
    }

    // 관리자 - 전체성적관리 : 강좌별 성적
    public MemberDto(String lectName, String grade, Long checkScore, Long assignScore,
                     Long testScore) {
        this.lectName = lectName;
        this.grade = grade;
        this.checkScore = checkScore;
        this.assignScore = assignScore;
        this.testScore = testScore;
    }


    //학생정보
    public MemberDto(Long studNowCr, Long studId, String userName,
                     LocalDate userBirthday, Gender userGender, String userPhoneNum,
                     String userEmail, String userAddr, String userId) {
        this.studNowCr = studNowCr;
        this.studId = studId;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
        this.userAddr = userAddr;
        this.userId = userId;
    }

    public MemberDto(Long lectId, String lectName, LocalDate lectStart, LocalDate lectEnd, Long lectCredit, boolean isActive, String userName, Long studId) {
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
