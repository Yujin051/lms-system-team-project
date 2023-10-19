package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

/**
 * 강좌성적 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Builder
@Table(name = "grade_info")
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class GradeInfo {

    //성적ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    //수강강좌ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private StudLectApply studLectApply;

    //평가등급
    @Column(name = "grade")
    @ColumnDefault("''")
    private String grade;

    //출석점수
    @Column(name = "check_score")
    @ColumnDefault("0")
    private Long checkScore;

    //과제점수
    @Column(name = "assign_score")
    @ColumnDefault("0")
    private Long assignScore;

    //시험점수
    @Column(name = "test_score")
    @ColumnDefault("0")
    private Long testScore;

//
//    @Builder
//    public  GradeInfo(LectInfo lectInfo, Student student, String grade, Long checkScore, Long assignScore, Long testScore) {
//        this.lectInfo = lectInfo;
//        this.student = student;
//        this. grade = grade;
//        this.checkScore = checkScore;
//        this.assignScore = assignScore;
//        this.testScore = testScore;
//    }
}
