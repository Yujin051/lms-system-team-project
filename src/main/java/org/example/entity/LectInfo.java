package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 강좌정보 엔티티
 * @author 임휘재
 */
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Data
@Table(name = "lect_info")
public class LectInfo {

    //강좌ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lect_id")
    private Long lectId;

    //강사ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_id")
    private Professor professor;

    //강좌명
    @Column(name = "lect_name")
    private String lectName;

    //강좌분류
    @Column(name = "lect_subject")
    private String lectSubject;

    //년도
    @Column(name = "lect_year")
    private String lectYear;

    //학기
    @Column(name = "lect_sem")
    private String lectSem;

    //학점
    @Column(name = "lect_credit")
    private Long lectCredit;

    //현재 수강자 수
    @Column(name = "lect_nownum")
    private Long lectNownum;

    //최대 수강자 수
    @Column(name = "lect_maxnum")
    private Long lectMaxnum;

    //수강신청 시작일정
    @Column(name = "enroll_start")
    private LocalDateTime enrollStart;

    //수강신청 종료일정
    @Column(name = "enroll_end")
    private LocalDateTime enrollEnd;

    //강좌 시작일시
    @Column(name = "lect_start")
    private LocalDateTime lectStart;

    //강좌 종료일시
    @Column(name = "lect_end")
    private LocalDateTime lectEnd;

    //강좌 운영상태
    @Column(name = "is_active")
    private String isActive;

    //과제 배점
    @Column(name = "lect_assign")
    private Long lectAssign;

    //출석 배점
    @Column(name = "lect_attend")
    private Long lectAttend;

    //시험 배점
    @Column(name = "lect_test")
    private Long lectTest;

}
