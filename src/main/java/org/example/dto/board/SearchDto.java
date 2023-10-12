package org.example.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
// 검색 dto
public class SearchDto {

    // 검색 값
    private String searchValue;
    // 검색 조건
    private String searchType;
//    // 검색 당시 보드 타입
//    private Long boardTypeId;

}
