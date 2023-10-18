package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의콘텐츠정보 엔티티
 * @author 신민기
 */
@Entity
@Data
@Table(name = "lms_conts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}