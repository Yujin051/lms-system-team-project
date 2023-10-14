package org.example.dto.board;

import lombok.*;
import org.example.entity.BoardArticle;
import org.springframework.data.domain.Page;

/**
 * @author 임승범
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageDto {

    private Page<BoardArticle> articles;
    private Integer nowPage;
    private Integer startPage;
    private Integer endPage;
    private Integer totalPage;
    private Long total;
//    int nowPage = articles.getPageable().getPageNumber() + 1 ;      // 현재 페이지
//    int startPage = Math.max(nowPage - 4 , 1);                      // 시작 페이지 (Math.max이용 , 비교 큰 값 할당)
//    int endPage = Math.min(nowPage + 5 , articles.getTotalPages()); // 마지막 페이지 (Math.max이용 , 비교 작은 값 할당)
//    int totalPage = articles.getTotalPages();   // 페이지 수
//    Long total = articles.getTotalElements();   // 게시글 갯수(레코드 수

    public PageDto(Page<BoardArticle> articles){
        this.articles = articles;
        this.nowPage = articles.getPageable().getPageNumber() + 1;
        this.startPage = Math.max(nowPage - 4 , 1);
        this.endPage = Math.min(nowPage + 5 , articles.getTotalPages());
        this.totalPage = articles.getTotalPages();
        this.total = articles.getTotalElements();
    }

}
