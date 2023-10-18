package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.constant.Gender;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {


    //sem_grade
    private String semGrade; //등급
    private String semYear; //년도
    private String semSem; //학기

    //student
    private Long studId; //수강생ID
    private Long studGrade; //학년
    private Long studNowCr; //현재수강학점
    private Long studMaxCr; //최대수강학점
    private Long studCreCpl; //이수학점

    //member
    private Long id; //회원ID
    private String userName; //학생명
    private String userId; //아이디
    private LocalDate userBirthday; //생년월일
    private Gender userGender; //성별
    private String userEmail; //이메일

    //lect_info
    private Long lectCredit; //학점
    private Long lectCheck; //출석배점




    //전체 학기성적 및 전체 현황
    public StudentDto(Long studId, String semGrade, String semYear, String semSem, Long studNowCr) {
        this.studId = studId;
        this.semGrade = semGrade;
        this.semYear = semYear;
        this.semSem = semSem;
        this.studNowCr = studNowCr;
    }
}
