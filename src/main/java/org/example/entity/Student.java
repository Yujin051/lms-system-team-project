package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 수강생 정보 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    //수강생ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stud_id")
    private Long studId;

    //회원ID
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;

    @OneToMany(mappedBy = "student")
    private List<SemGrade> semGrade;

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
