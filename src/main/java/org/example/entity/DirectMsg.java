package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.board.DirectMsgDto;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 임승범
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "direct_msg")
@Getter
@Entity
@ToString
public class DirectMsg {

    // 쪽지 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id", updatable = false)
    private Long id;

    //이전쪽지 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msg_org_id", nullable = true)
    private DirectMsg orgMsg;

    // 발신자 id
    @OneToOne
    @JoinColumn(name = "send_id" , nullable = false)
    private Member sendId;

    // 수신자 id
    @OneToOne
    @JoinColumn(name = "recv_id" , nullable = false)
    private Member recvId;

    // 작성일시
    @CreatedDate
    @Column(name = "send_at" , nullable = false)
    private LocalDateTime sendAt;

    // 수신자 읽은날짜
    @Column(name = "recv_at" , nullable = true)
    private LocalDateTime recvAt;

    // 쪽지 제목
    @Column(name = "msg_title" , nullable = false)
    private String msgTitle;

    // 쪽지 내용
    @Column(name = "msg_cont" , nullable = false , columnDefinition = "TEXT")
    private String msgCont;

    // 발신자 쪽지 삭제여부
    @Column(name = "send_del_yn" , nullable = false)
    @ColumnDefault("false")
    private Boolean sendDelYn;

    // 수신자 쪽지 삭제여부
    @Column(name = "recv_del_yn" , nullable = false)
    @ColumnDefault("false")
    private Boolean recvDelYn;

    @Builder
    public DirectMsg(DirectMsg orgMsg , Member sendId , Member recvId , String msgTitle ,
                     String msgCont , Boolean sendDelYn , Boolean recvDelYn){

        this.orgMsg = orgMsg;
        this.sendId = sendId;
        this.recvId = recvId;
        this.msgTitle = msgTitle;
        this.msgCont = msgCont;
        this.sendDelYn = sendDelYn;
        this.recvDelYn = recvDelYn;
    }

    // 발신자 쪽 메시지 논리삭제
    public void deleteSend(){
        this.sendDelYn = true;
    }

    // 수신자 쪽 메시지 논리삭제
    public void deleteRecv(){
        this.recvDelYn = true;
    }


}
