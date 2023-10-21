package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 강의콘텐츠정보 엔티티
 * @author 신민기
 */
@Entity
@Data
@Table(name = "lms_conts")
public class LmsConts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conts_no")
    private Long contsNo;

    @Column(name = "conts_name")
    private String contsName;

    @Column(name = "conts_detail")
    private String contsDetail;

    @Column(name = "conts_time")
    private int contsTime;

    @Column(name = "conts_yout")
    private String contsYout;

    public LmsConts(String contsName, String contsDetail, int contsTime, String contsYout) {
        this.contsName = contsName;
        this.contsDetail = contsDetail;
        this.contsTime = contsTime;
        this.contsYout = contsYout;
    }
}
