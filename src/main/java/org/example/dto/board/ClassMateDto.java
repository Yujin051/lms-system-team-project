package org.example.dto.board;

import lombok.*;

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


}
