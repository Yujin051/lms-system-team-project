package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="stud_lect_apply")
public class LectApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apply_id")
    private Long id;

    // 강좌 ID 외래키


    // 수강생 ID 외래키

}
