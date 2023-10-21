package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 수강생차시진도 엔티티
 * @author 신민기
 */
@Entity
@Setter @Getter
@Table(name = "stud_lect_prog")
@NoArgsConstructor
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
    private double fnlPosi;

    /* 최대재생위치 */
    @Column(name = "max_posi")
    private double maxPosi;

    /* 출석 상태 */
    @Column(name = "is_checked")
    private Boolean isChecked = false;

    public StudLectProg(Student student, LectNth lectNth, LectInfo lectInfo, int fnlPosi, int maxPosi, boolean isChecked, LocalDateTime checkDate) {
        this.student = student;
        this.lectNth = lectNth;
        this.lectInfo = lectInfo;
        this.fnlPosi = fnlPosi;
        this.maxPosi = maxPosi;
        this.isChecked = isChecked;
        this.checkDate = checkDate;
    }

    /* 출석 날짜 */
    @Column(name = "check_date")
    private LocalDateTime checkDate;

    /* 진행률 */
    @Column(name = "progress")
    private double progress;

    @Builder
    public StudLectProg(double fnlPosi, double maxPosi, Boolean isChecked,
                        LocalDateTime checkDate, double progress) {
        this.fnlPosi = fnlPosi;
        this.maxPosi = maxPosi;
        this.isChecked = isChecked;
        this.checkDate = checkDate;
        this.progress = progress;
    }


}
