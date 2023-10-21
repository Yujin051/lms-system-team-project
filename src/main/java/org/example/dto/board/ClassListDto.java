package org.example.dto.board;

import lombok.*;

/**
 * @author 임승범
 */

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
// 강사용 쪽지 수업 리스트 DTO
public class ClassListDto {

    // 수업 id
    private Long lectInfo;
    // 수업명
    private String lectName;

//    public ClassListDto(Long lectInfo , String lectName){
//        this.lectInfo = lectInfo;
//        this.lectName = lectName;
//
//    }

}
