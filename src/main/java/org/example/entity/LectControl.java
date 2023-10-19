package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="nth_ctrl")
public class LectControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ctrl_id")
    private Long id;

    // 차시 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nth_id")
    private LectNth lectNth;

    // 강좌 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lect_id")
    private LectInfo lectInfo;

    // 영상을 멈춘 시점
    @Column(name="stop_posi")
    private Long stopPosition;

    // 멈췄을 때 메세지 출력
    @Column(name="stop_msg")
    private String stopMsg;

    // 필요한 정답 확인?
    @Column(name="ans")
    private String answer;
}
