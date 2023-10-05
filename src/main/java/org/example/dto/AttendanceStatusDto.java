package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceStatusDto {

    private String lectName;
    private boolean isActive;
    private Long lectId;
    private String lectSubject;
    private LocalDate lectStart;
    private LocalDate lectEnd;
    private Long lectCredit;
    private Long studId;


}
