package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    //수강생ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stud_id")
    private Long studId;

    //회원ID
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
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

    public Student(Member member, Long studGrade, Long studMaxCr,
                   Long studNowCr, Long studCreCpl) {
        this.member = member;
        this.studGrade = studGrade;
        this.studMaxCr = studMaxCr;
        this.studNowCr = studNowCr;
        this.studCreCpl = studCreCpl;
    }
}
