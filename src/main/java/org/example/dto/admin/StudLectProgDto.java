package org.example.dto.admin;

import lombok.Data;
import org.example.entity.StudLectProg;

@Data
public class StudLectProgDto {

    private Long magId;
    private Integer fnlPosi; //최종재생위치(마지막 재생 시간을 초로)
    private Integer maxPosi; //최대재생위치(영상을 본 시간을 5초마다 저장해야함.)

    public StudLectProgDto(Long magId) {
        this.magId = magId;
    }

    // 최종재생위치 저장
    public StudLectProg toEntity() {
        return StudLectProg.builder()
                .fnlPosi(fnlPosi)
                .maxPosi(maxPosi)
                .build();
    }
}
