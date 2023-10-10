package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LectureListDto {
    private boolean isActive;
    private String lectName;
    private int lectElem;
    private Long lectCredit;
    private String lectSubject;
    private Long lectId;
    private LocalDateTime lectStart;
    private LocalDateTime lectEnd;
    private String userName;
}
