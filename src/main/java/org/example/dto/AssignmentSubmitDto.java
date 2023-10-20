package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Assignments;
import org.example.entity.Member;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentSubmitDto {
    private Long id;
    private Member member;
    private Assignments assignments;
    private String content;
    private String originalName;
    private String savedName;
    private LocalDate dateTime;
    private boolean isSubmit;
    private String feedback;

    @Builder
    public AssignmentSubmitDto(Member member, Assignments assignments, String content, String originalName, String savedName, LocalDate dateTime, boolean isSubmit, String feedback) {
        this.member = member;
        this.assignments = assignments;
        this.content = content;
        this.originalName = originalName;
        this.savedName = savedName;
        this.dateTime = dateTime;
        this.isSubmit = isSubmit;
        this.feedback = feedback;
    }

}
