package org.example.dto.board;

import lombok.*;
import org.example.entity.DirectMsg;
import org.example.entity.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author 임승범
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// 쪽지 dto
public class DirectMsgDto {

    // 쪽지 id
    private Long id;
    // 이전쪽지 id
    private DirectMsg orgMsg;
    // 발신자 id
    private Member sendId;
    // 수신자 id
    private Member recvId;
    // 수신자 리스트
    private List<String> toSome;
    // 작성일시
    private LocalDateTime sendAt;
    // 수신자 읽은날짜
    private LocalDateTime recvAt;
    // 쪽지 제목
    private String msgTitle;
    // 쪽지 내용
    private String msgCont;
    // 발신자 쪽지 삭제여부
    private Boolean sendDelYn;
    // 수신자 쪽지 삭제여부
    private Boolean recvDelYn;

    public DirectMsg toEntity(){
        return DirectMsg.builder()
                .sendId(sendId)
                .recvId(recvId)
                .msgTitle(msgTitle)
                .msgCont(msgCont)
                .orgMsg(orgMsg)
                .sendDelYn(sendDelYn == null || sendDelYn == false? false : true)
                .recvDelYn(recvDelYn == null || recvDelYn == false? false : true)
                .build();
    }


}
