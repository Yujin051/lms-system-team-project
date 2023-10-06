package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceStatusDto {

    /* lectInfo 강좌정보*/
    private String lectName;
    /* lectInfo 강좌운영상태*/
    private boolean isActive;
    /* lectInfo 강좌ID*/
    private Long lectId;
    /* lectInfo 강좌 분류(과목명) */
    private String lectSubject;
    /* lectInfo 강좌 시작일시 */
    private LocalDate lectStart;
    /* lectInfo 강좌 종료일시*/
    private LocalDate lectEnd;
    /* lectInfo 학점*/
    private Long lectCredit;
    /* student 수강생Id*/
    private Long studId;


}
