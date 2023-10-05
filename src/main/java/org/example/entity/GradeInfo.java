package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 강좌성적 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Table(name = "grade_info")
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

}
