package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class StudLectProgDto {

    private Long magId; //수강생차시진도 기본키
    private double fnlPosi; //최종재생위치(마지막 재생 시간을 초로)
    private double maxPosi; //최대재생위치
    private Boolean isChecked; //출석상태
    private LocalDateTime checkDate; //출석날짜(출석상태가 true된 시점의 날짜 및 시간)

    public StudLectProgDto(Long magId, double maxPosi, double fnlPosi) {
        this.magId = magId;
        this.maxPosi = maxPosi;
        this.fnlPosi = fnlPosi;
    }
}
