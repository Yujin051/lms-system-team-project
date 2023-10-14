package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LmsContsDto {

        private Long nthId;
        private Long contsNo;
        private Long nthSequence;
        private String contsName;
        private String nthName;
        private String contsYout;
        private int contsTime;


}
