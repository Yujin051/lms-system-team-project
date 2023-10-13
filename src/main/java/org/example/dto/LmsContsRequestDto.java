package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.entity.LectInfo;
import org.example.entity.LectNth;
import org.example.entity.LmsConts;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LmsContsRequestDto {

    private Long nthId;
    private Long contsNo;
    private Long nthSequence;
    private String contsName;
    private int contsTime;
    private String contsYout;
    private String contsDetail;
    private String nthName;
    private LectInfo lectInfo;
    private LmsConts lmsConts;



    public LectNth toEntity() {
        return LectNth.builder()
                .lectInfo(lectInfo)
                .lmsConts(lmsConts)
                .nthName(nthName)
                .nthSequence(nthSequence)
                .build();
    }
}
