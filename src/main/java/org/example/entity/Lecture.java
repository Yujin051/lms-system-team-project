package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Table(name= "lect_info")
@Getter
@Setter
@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lect_id", updatable = false)
    private Long lectId;

    @ManyToOne
    @JoinColumn(name="prof_id")
    private Professor professor;

    @Column(name = "lect_name")
    private String lectName;

    @Column(name = "lect_subject")
    private String lectSubject;

    @Column(name = "lect_year")
    private String lectYear;

    @Column(name = "lect_sem")
    private String lectSem;

    @Column(name = "lect_credit")
    private int lectCredit;

    @Column(name = "lect_nownum")
    private int lectNownum;

    @Column(name = "lect_maxnum")
    private int lectMaxnum;

    @Column(name = "enroll_start")
    private LocalDate enrollStart;

    @Column(name = "enroll_end")
    private LocalDate enrollEnd;

    @Column(name = "lect_start")
    private LocalDate lectStart;

    @Column(name = "lect_end")
    private LocalDate lectEnd;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "lect_assign")
    private int lectAssign;

    @Column(name = "lect_attend")
    private int lectAttend;

    @Column(name = "lect_test")
    private int lectTest;

    @Builder
    public Lecture(Long lectId, Professor professor, String lectName, String lectSubject, String lectYear, String lectSem, int lectCredit, int lectNownum, int lectMaxnum, LocalDate enrollStart, LocalDate enrollEnd, LocalDate lectStart, LocalDate lectEnd, boolean isActive, int lectAssign, int lectAttend, int lectTest) {
        this.lectId = lectId;
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
        this.lectAttend = lectAttend;
        this.lectTest = lectTest;
    }
}
