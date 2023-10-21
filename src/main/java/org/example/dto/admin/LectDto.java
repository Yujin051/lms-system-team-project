package org.example.dto.admin;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.LectInfo;
import org.example.entity.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LectDto {

    //lect_info
    private Long lectId; //강좌ID
    private String lectName; //강좌명
    private String lectSubject; //과목명
    private LocalDate lectStart; //강좌시작일시
    private LocalDate lectEnd; //강좌종료일시
    private Boolean isActive; //강좌운영상태
    private Long lectCredit; //강좌의 학점

    //student
    private Long studId; //수강생ID
    private Long studNowCr; //학생의 해당 강좌에 대한 현재수강학점
    private Long studGrade; //수강생 학년

    //member
    private Member member;
    private Long memberId; //회원ID
    private String userName; //이름
    private String userId; //학번




    // 온라인수강현황 : 학습강좌 조회
    public LectDto(Long lectId, String lectName, String lectSubject,
                   LocalDate lectStart, LocalDate lectEnd, boolean isActive) {
        this.lectId = lectId;
        this.lectName = lectName;
        this.lectSubject = lectSubject;
        this.lectStart = lectStart;
        this.lectEnd = lectEnd;
        this.isActive = isActive;
    }

    // 온라인수강현황 : 전체이수현황
    public LectDto(String lectName, Long lectCredit, Long studGrade,
                   String userName, String userId) {
        this.lectName = lectName;
        this.lectCredit = lectCredit;
        this.studGrade = studGrade;
        this.userName = userName;
        this.userId = userId;
    }

}
