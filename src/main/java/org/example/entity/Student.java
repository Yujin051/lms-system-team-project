package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * 수강생 정보 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Table(name = "student")
public class Student {

    //수강생ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stud_id")
    private Long studId;

    //회원ID
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;

    //수강생 학년
    @Column(name = "stud_grade")
    private Long studGrade;

    //수강 가능학점
    @Column(name = "stud_maxcr")
    private Long studMaxCr;

    //현재 수강학점
    @Column(name = "stud_nowcr")
    private Long studNowCr;

    //이수학점
    @Column(name = "stud_crecpl")
    private Long studCreCpl;

}
