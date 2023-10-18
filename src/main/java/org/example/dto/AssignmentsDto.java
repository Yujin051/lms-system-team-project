package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.LectInfo;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
public class AssignmentsDto {
    private LectInfo lectInfo;

    private String name;

    private String detail;

    private LocalDate start;

    private LocalDate end;

    private boolean isActive;

    private boolean isSubmit;

    private String originFilename;

    private String savedFilename;

    @Builder
    public AssignmentsDto(LectInfo lectInfo, String name, String detail, LocalDate start, LocalDate end, boolean isActive, boolean isSubmit, String originFilename, String savedFilename) {
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
