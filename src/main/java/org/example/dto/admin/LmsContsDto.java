package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LmsContsDto {
    private Long contsNo;
    private String contsName;
    private String contsDetail;
    private String contsYout;
    private int contsTime;
    private String lectName;

    public LmsContsDto(String contsName, String contsYout, int contsTime, String lectName) {
        this.contsName = contsName;
        this.contsYout = contsYout;
        this.contsTime = contsTime;
        this.lectName = lectName;
    }
}
