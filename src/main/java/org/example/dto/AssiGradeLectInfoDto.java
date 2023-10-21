package org.example.dto;

import lombok.Getter;

@Getter
public class AssiGradeLectInfoDto {
    // 강좌번호
    private Long lectId;
    // 강좌명
    private String lectName;
    // 성적입력여부
    private boolean isRecord;

    public AssiGradeLectInfoDto(Long lectId, String lectName, boolean isRecord) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.isRecord = isRecord;
    }
}