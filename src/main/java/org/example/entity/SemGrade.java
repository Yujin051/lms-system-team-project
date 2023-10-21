package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 학기별 성적 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Table(name = "sem_grade")
@AllArgsConstructor
@NoArgsConstructor
public class SemGrade {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sem_id")
    private Long semId;

    //수강생ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stud_id")
    private Student student;

    //년도
    @Column(name = "sem_year")
    private String semYear;

    //학기
    @Column(name = "sem_sem")
    private String semSem;

    // 학기별 이수학점
    @Column(name = "sem_crecpl")
    private Long semCrecpl;

    // 학기별 평균학점
    @Column(name = "sem_avg_crecpl")
    private double semAvgCrecpl;

    @Builder
    public SemGrade(Student student, String semYear, String semSem) {
        this.student = student;
        this.semYear = semYear;
        this.semSem = semSem;
    }

    public SemGrade(Student student, String semYear, String semSem, double semAvgCrecpl) {
        this.student = student;
        this.semYear = semYear;
        this.semSem = semSem;
        this.semAvgCrecpl = semAvgCrecpl;
    }
}
