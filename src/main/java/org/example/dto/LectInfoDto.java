package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.LectInfo;

@Getter
@Setter
public class LectInfoDTO {

    private Long lectId;
    private String lectName;
    private String lectSubject;
    private String lectYear;
    private String lectSem;
    private Long lectCredit;

    // getter and setter methods

    public static LectInfoDTO fromLectInfo(LectInfo lectInfo) {
        LectInfoDTO dto = new LectInfoDTO();
        dto.setLectId(lectInfo.getLectId());
        dto.setLectName(lectInfo.getLectName());
        dto.setLectSubject(lectInfo.getLectSubject());
        dto.setLectYear(lectInfo.getLectYear());
        dto.setLectSem(lectInfo.getLectSem());
        dto.setLectCredit(lectInfo.getLectCredit());
        return dto;
    }
}