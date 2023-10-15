package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.StudLectProg;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class StudLectProgDto {

    private Long magId; //수강생차시진도 기본키
    // 최종재생위치와 최대재생위치를 둘다 5초마다 저장하는데
    private double fnlPosi; //최종재생위치(마지막 재생 시간을 초로)
    private double maxPosi; //최대재생위치(영상을 본 시간을 5초마다 저장해야함.)
    private Boolean isChecked;
    private LocalDate checkDate;

    public StudLectProgDto(Long magId) {
        this.magId = magId;
    }


    // 최종재생위치 저장
    public StudLectProg toEntity() {
        return StudLectProg.builder()
                .fnlPosi(fnlPosi)
                .maxPosi(maxPosi)
                .isChecked(isChecked)
                .checkDate(checkDate)
                .build();
    }
}
