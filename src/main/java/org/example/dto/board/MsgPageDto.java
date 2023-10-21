package org.example.dto.board;

import lombok.*;
import org.example.entity.DirectMsg;
import org.springframework.data.domain.Page;

/**
 * @author 임승범
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MsgPageDto {

    private Page<DirectMsg> msgs;
    private Integer nowPage;
    private Integer startPage;
    private Integer endPage;
    private Integer totalPage;
    private Long total;

    public MsgPageDto(Page<DirectMsg> msgs){
        this.msgs = msgs;
        this.nowPage = msgs.getPageable().getPageNumber() + 1;
        this.startPage = Math.max(nowPage - 4 , 1);
        this.endPage = Math.min(nowPage + 5 , msgs.getTotalPages());
        this.totalPage = msgs.getTotalPages();
        this.total = msgs.getTotalElements();
    }

}
