package org.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class StudLectProgDto {

    private Long id;
    private Long studId;
    private Long nthId;
    private Long lectId;
    private int fnlPosi;
    private int maxPosi;
    private boolean isChecked;
    private LocalDateTime checkDate;
}
