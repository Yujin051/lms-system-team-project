package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckSemGradeDto {
    private String semYear;
    private String semSem;
//    private String grade;
//    private Long lectCredit;

//    public CheckSemGradeDto(String semYear, String semSem) {
//        this.semYear = semYear;
//        this.semSem = semSem;
//    }
}
