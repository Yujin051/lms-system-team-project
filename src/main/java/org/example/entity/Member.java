package org.example.entity;


import org.example.constant.Gender;
import org.example.constant.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.example.dto.MemberFormDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ConnectionBuilder;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Table(name= "member")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name= "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name= "user_pw")
    private String userPassword;

    @Column(name= "user_name")
    private String userName;

    @Column(name= "user_phonenum")
    private String userPhoneNum;

    @CreatedDate
    @Column(name = "user_regdate")
    private LocalDate userRegDate;

    @Column(name= "user_addr")
    private String userAddr;

    @Column(name= "user_birthday")
    private LocalDate userBirthday;

    @Column(name= "user_email")
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender")
    private Gender userGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private RoleType userRole;


    @Builder
    public Member(String userId, String userPassword, String userName, String userPhoneNum,
                  String userAddr, String userEmail, LocalDate userRegDate, LocalDate userBirthday, Gender userGender, RoleType userRole) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userAddr = userAddr;
        this.userEmail = userEmail;
        this.userRegDate = userRegDate;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userRole = userRole;
    }

    public static Member createStudent(MemberFormDto memberFormDto , PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .userId(memberFormDto.getUserId())
                .userPassword(passwordEncoder.encode(memberFormDto.getUserPassword()))
                .userName(memberFormDto.getUserName())
                .userPhoneNum(memberFormDto.getUserPhoneNum())
                .userAddr(memberFormDto.getUserAddr())
                .userEmail(memberFormDto.getUserEmail())
                .userBirthday(memberFormDto.getUserBirthday())
                .userGender(memberFormDto.getUserGender())
                .userRole(RoleType.USER)
                .build();
        return member;
    }

    public static Member createProf(MemberFormDto memberFormDto , PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .userId(memberFormDto.getUserId())
                .userPassword(memberFormDto.getUserPassword())
                .userPassword(passwordEncoder.encode(memberFormDto.getUserPassword()))
                .userName(memberFormDto.getUserName())
                .userPhoneNum(memberFormDto.getUserPhoneNum())
                .userAddr(memberFormDto.getUserAddr())
                .userEmail(memberFormDto.getUserEmail())
                .userBirthday(memberFormDto.getUserBirthday())
                .userGender(memberFormDto.getUserGender())
                .userRole(RoleType.TEACHER)
                .build();
        return member;
    }



}
