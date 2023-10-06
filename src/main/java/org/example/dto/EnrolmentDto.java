package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.entity.Member;
import org.example.entity.Professor;

@Getter
public class EnrolmentDto {

    // 교원이름
    private String userName;
    // 강좌번호
    private Long lectId;
    // 강좌이름
    private String lectName;
    // 강좌 학점
    private Long lectCredit;
    // 현재 수강자수
    private Long lectNownum;
    // 최대 수강자수
    private Long lectMaxnum;


    public EnrolmentDto(String userName, Long lectId, String lectName, Long lectCredit, Long lectNownum, Long lectMaxnum) {
        this.userName = userName;
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectCredit = lectCredit;
        this.lectNownum = lectNownum;
        this.lectMaxnum = lectMaxnum;
    }
}
