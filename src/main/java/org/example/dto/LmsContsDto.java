package org.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LmsContsDto {

        private Long contsNo;
        private String contsName;
        private String contsDetail;
        private int contsTime;
        private String contsYout;

}
