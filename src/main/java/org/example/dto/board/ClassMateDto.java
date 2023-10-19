package org.example.dto.board;

import lombok.*;
import org.example.entity.Member;

import java.util.List;

/**
 * @author 임승범
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
// 수업 참가자 요청 DTO
public class ClassMateDto {

    // 수업명
//    private String ClassName;

    // 수업 번호 lect_id
    private Long classId;
    // 수업 참가자 이름
    private String memberName;
    // 수업 참가자 id (memberId)
    private Long memberId;
    // 수업 참가자 loginId
    private String loginId;


}
