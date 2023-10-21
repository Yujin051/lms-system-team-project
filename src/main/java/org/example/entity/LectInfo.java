package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 강좌정보 엔티티
 * @author 임휘재
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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
    @JsonIgnore
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
    private LocalDate lectStart;

    //강좌 종료일시
    @Column(name = "lect_end")
    private LocalDate lectEnd;

    //강좌 운영상태
    @Column(name = "is_active")
    private boolean isActive;

    //과제 배점
    @Column(name = "lect_assign")
    private Long lectAssign;

    //출석 배점
    @Column(name = "lect_check")
    private Long lectCheck;

    //시험 배점
    @Column(name = "lect_test")
    private Long lectTest;

    // 수강 대상 학년
    @Column(name="lect_elem")
    private int lectElem;

    //성적입력여부
    @Column(name = "is_record")
    private boolean isRecord;

    @Builder
    public LectInfo(Professor professor, String lectName, String lectSubject, String lectYear, String lectSem,
                    Long lectCredit, Long lectNownum, Long lectMaxnum, LocalDateTime enrollStart, LocalDateTime enrollEnd,
                    LocalDate lectStart, LocalDate lectEnd, boolean isActive, Long lectAssign, Long lectCheck, Long lectTest, boolean isRecord) {

        this.professor = professor;
        this.lectName = lectName;
        this.lectSubject = lectSubject;
        this.lectYear = lectYear;
        this.lectSem = lectSem;
        this.lectCredit = lectCredit;
        this.lectNownum = lectNownum;
        this.lectMaxnum = lectMaxnum;
        this.enrollStart = enrollStart;
        this.enrollEnd = enrollEnd;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.isActive = isActive;
        this.lectAssign = lectAssign;
        this.lectCheck = lectCheck;
        this.lectTest = lectTest;
        this.isRecord = isRecord;
    }

    public void Plus(){
        this.lectNownum = this.lectNownum +1;
    }
    public void minus(){
        this.lectNownum = this.lectNownum -1;
    }

    public LectInfo(Professor professor, String lectName,
                    String lectSubject, String lectYear, String lectSem,
                    Long lectCredit, Long lectNownum, Long lectMaxnum,
                    LocalDateTime enrollStart, LocalDateTime enrollEnd,
                    LocalDate lectStart, LocalDate lectEnd,
                    Boolean isActive) {
        this.professor = professor;
        this.lectName = lectName;
        this.lectSubject = lectSubject;
        this.lectYear = lectYear;
        this.lectSem = lectSem;
        this.lectCredit = lectCredit;
        this.lectNownum = lectNownum;
        this.lectMaxnum = lectMaxnum;
        this.enrollStart = enrollStart;
        this.enrollEnd = enrollEnd;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.isActive = isActive;
    }

}
