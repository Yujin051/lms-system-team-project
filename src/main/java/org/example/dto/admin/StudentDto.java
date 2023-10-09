package org.example.dto.admin;

import org.example.constant.Gender;

import java.time.LocalDate;

public class StudentDto {

    //student
    private Long studId; //수강생ID
    private Long studGrade; //학년
    private Long studMaxCr; //최대수강학점
    private Long studNowCr; //현재수강학점
    private Long studCreCpl; //이수학점
    //member
    private Long id; //회원ID
    private String userName; //학생명
    private String userId; //학번
    private LocalDate userBirthday; //생년월일
    private Gender userGender; //성별
    private String userEmail; //이메일

    //lect_info
    private String lectYear; //년도
    private String lectSem; //학기
    private Long lectCredit; //학점
    private Long lectCheck; //출석배점

    // sem_grade
    private String semYear;
    private String semSem;

    public StudentDto(String userName, String userId, LocalDate userBirthday, Gender userGender, String userEmail, Long studGrade) {
        this.userName = userName;
        this.userId = userId;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.studGrade = studGrade;
    }


}
