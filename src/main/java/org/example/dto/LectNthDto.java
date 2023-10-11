package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.LmsConts;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectNthDto {


    private Long lectId;
    private String lectName;
    private String lectSubject;
    private LocalDateTime enrollStart;
    private LocalDateTime enrollEnd;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private boolean isActive;
    private Long nthSequence;
    private String nthName;
    /*private LmsConts lmsConts;*/
    private int contsTime;

}