package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.AssignmentsDto;

import java.time.LocalDate;

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
    private Long assiId;


    // 강좌 키 외래키로
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
    public Assignments(long assiId, LectInfo lectInfo, String name, String detail, LocalDate start, LocalDate end, boolean isActive, boolean isSubmit, String originFilename, String savedFilename) {
        this.assiId = assiId;
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

    public static Assignments createAssignments(AssignmentsDto assignmentsDto) {
        Assignments assignments = Assignments.builder()
                .lectInfo(assignmentsDto.getLectInfo())
                .name(assignmentsDto.getName())
                .detail(assignmentsDto.getDetail())
                .start(assignmentsDto.getStart())
                .end(assignmentsDto.getEnd())
                .isActive(assignmentsDto.isActive())
                .isSubmit(assignmentsDto.isSubmit())
                .originFilename(assignmentsDto.getOriginFilename())
                .savedFilename(assignmentsDto.getSavedFilename())
                .build();
        return assignments;
    }
}