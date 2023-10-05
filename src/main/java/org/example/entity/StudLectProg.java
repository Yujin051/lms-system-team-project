package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 수강생차시진도 엔티티
 * @author 신민기
 */
@Entity
@Data
@Table(name = "stud_lect_prog")
public class StudLectProg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mag_id")
    private Long magId;

    /* 수강생 번호 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stud_id")
    private Student student;

    /* 차시ID */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nth_id")
    private LectNth lectNth;

    /* 강좌ID */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lect_id")
    private LectInfo lectInfo;

    /* 최종재생위치 */
    @Column(name = "fnl_posi")
    private int fnlPosi;

    /* 최대재생위치 */
    @Column(name = "max_posi")
    private int maxPosi;

    /* 출석 상태 */
    @Column(name = "is_checked")
    private boolean isChecked;

    /* 출석 날짜 */
    @Column(name = "check_date")
    private LocalDateTime checkDate;
}
