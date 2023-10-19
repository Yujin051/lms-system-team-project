package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 학기별 성적 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Table(name = "sem_grade")
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

    //등급
    @Column(name = "sem_rating")
    private String semRating;

    //평균
    @Column(name = "sem_avg")
    private Long semAvg;

    public SemGrade(Student student, String semYear, String semSem, String semRating) {
        this.student = student;
        this.semYear = semYear;
        this.semSem = semSem;
        this.semRating = semRating;
    }
}
