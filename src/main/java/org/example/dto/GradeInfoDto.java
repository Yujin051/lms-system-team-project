package org.example.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeInfoDto {

    private Long lectId;
    private Long checkScore;
    private Long assignScore;
    private Long testScore;
    private Long gradeId;

}
