package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.repository.AssignmentsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="assign_info")
public class Assignments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_id")
    private Long id;

    // 강좌 키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lect_id")
    private LectInfo lectInfo;

    // 과제 이름
    @Column(name = "assign_name")
    private String name;

    // 과제 대한 설명
    @Column(name = "assign_detail")
    private String detail;

    // 과제 시작 일시
    @Column(name = "assign_start")
    private LocalDate start;

    // 과제 마감 일시
    @Column(name = "assign_end")
    private LocalDate end;

    // 과제가 현재 진행중인지
    @Column(name = "is_active")
    private boolean isActive;

    // 과제가 제출되었는지
    @Column(name = "is_submit")
    private boolean isSubmit;

    // 교수가 제출하는 과제에 첨부하는 과제파일
    @Column(name = "origin_filename")
    private String originFilename;

    @Column(name = "saved_filename")
    private String savedFilename;


    @Builder
    public Assignments(long id, LectInfo lectInfo, String name, String detail, LocalDate start, LocalDate end, boolean isActive, boolean isSubmit, String originFilename, String savedFilename) {
        this.id = id;
        this.lectInfo = lectInfo;
        this.name = name;
        this.detail = detail;
        this.start = start;
        this.end = end;
        this.isActive = isActive;
        this.isSubmit = isSubmit;
        this.originFilename = originFilename;
        this.savedFilename = savedFilename;
    }
}