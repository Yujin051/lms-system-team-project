package org.example.dto.admin;

import lombok.Data;

import java.util.List;

@Data
public class DeleteApplyStudentDto {
    private Long lectId;
    private List<ApplyStudentDto> deleteRows;
}
