package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의수강신청 엔티티
 * @author 임휘재
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stud_lect_apply")
public class StudLectApply {

    //수강강좌ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long applyId;

    //수강생번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stud_id")
    private Student student;

    //강좌ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lect_id")
    private LectInfo lectInfo;
}
