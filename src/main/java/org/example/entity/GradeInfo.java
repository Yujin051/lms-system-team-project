package org.example.entity;

import jakarta.persistence.*;
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
public class GradeInfo {

    //성적ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    //강의수강신청ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private StudLectApply studLectApply;

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

    public GradeInfo(StudLectApply studLectApply, String grade,
                     Long checkScore, Long testScore, Long assignScore,
                     boolean isRecord) {
        this.studLectApply = studLectApply;
        this.grade = grade;
        this.checkScore = checkScore;
        this.testScore = testScore;
        this.assignScore = assignScore;
        this.isRecord = isRecord;
    }
}
