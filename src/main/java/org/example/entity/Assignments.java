package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assign_info")
public class Assignments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_id")
    private Long id;

    // 강좌 키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lect_id")
    private LectInfo lectInfo;

    // 과제 이름
    @Column(name = "assign_name")
    private String name;

    // 과제 대한 설명
    @Column(name = "assign_detail")
    private String detail;

    // 과제 시작 일시
    @Column(name = "assign_start")
    private LocalDateTime start;

    // 과제 마감 일시
    @Column(name = "assign_end")
    private LocalDateTime end;

    // 과제가 현재 진행중인지
    @Column(name = "is_active")
    private boolean isActive;

    // 과제가 제출되었는지
    @Column(name = "is_submit")
    private boolean isSubmit;

}
