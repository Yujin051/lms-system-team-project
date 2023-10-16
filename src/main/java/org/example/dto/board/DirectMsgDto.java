package org.example.dto.board;

import lombok.*;
import org.example.entity.DirectMsg;
import org.example.entity.Member;

import java.time.LocalDateTime;

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


}
