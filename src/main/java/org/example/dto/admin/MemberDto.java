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
public class MemberDto {

    private Long studId;
    private String userName;
    private LocalDate userBirthday;
    private Gender userGender;
    private String userPhoneNum;
    private String userEmail;
    private String userAddr;
    private double studCreCpl;
    private String userId;
    private Long id;

    // 관리자 : 학생정보
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
}
