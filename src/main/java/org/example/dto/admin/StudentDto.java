package org.example.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentDto {

    private Long studId;
    private Long id;
    private Long studGrade;
    private Long studMaxCr;
    private Long studNowCr;
    private Long studCreCpl;
}
