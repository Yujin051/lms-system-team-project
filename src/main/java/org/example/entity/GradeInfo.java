package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강좌성적 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Table(name = "grade_info")
@NoArgsConstructor
@AllArgsConstructor
public class GradeInfo {

    //성적ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    //강좌ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lect_id")
    private LectInfo lectInfo;

    //수강생ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stud_id")
    private Student student;

    //평가등급
    @Column(name = "grade")
    private String grade;

    //출석점수
    @Column(name = "check_score")
    private Long checkScore;

    //과제점수
    @Column(name = "assign_score")
    private Long assignScore;

    //시험점수
    @Column(name = "test_score")
    private Long testScore;

    //성적입력여부
    @Column(name = "is_record")
    private boolean isRecord;

    @Builder
    public  GradeInfo(LectInfo lectInfo, Student student, String grade, Long checkScore, Long assignScore, Long testScore, boolean isRecord) {
        this.lectInfo = lectInfo;
        this.student = student;
        this. grade = grade;
        this.checkScore = checkScore;
        this.assignScore = assignScore;
        this.testScore = testScore;
        this.isRecord = isRecord;
    }
}
