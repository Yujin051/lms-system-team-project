package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectNthDto {

    private String lectName;
    private String lectSem;
    private LocalDateTime enrollStart;
    private LocalDateTime enrollEnd;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private boolean isActive;
    private String nthName;
    private Long nthSequence;



}
