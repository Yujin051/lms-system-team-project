package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="assign_submit")
public class AssignSubmit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="submit_id")
    private Long id;

    // 과제 엔티티와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "assign_id")
    private Assignments assignments;

    // 과제 제출 시 설명 등 내용
    @Column(name="submit_cont")
    private String content;

    // 제출한 첨부파일 원래 이름
    @Column(name="submit_orignm")
    private String originalName;

    // 제출한 첨부파일 DB 저장 이름
    @Column(name="submit_savenm")
    private String savedName;

    // 과제 제출일시
    @Column(name="submit_datetime")
    private LocalDateTime dateTime;

    // 과제 피드백
    private String feedback;
}
