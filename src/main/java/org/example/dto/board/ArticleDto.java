package org.example.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.BoardArticle;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
// 게시글 리스트 출력 DTO
public class ArticleDto {

    // 게시글 id
    private Long id;
    // 게시판 종류
    private String type;
    // 게시판 종류 id
    private Long boardInfo;
    // 게시글 제목
    private String title;
    // 게시글 작성자 이름
    private String writer;
    // 게시글 작성자 id
    private Long memberId;
    // 게시글 작성 시간
    private LocalDateTime articleAt;
    // 게시글 내용
    private String content;
    // 게시글 공개여부
    private Boolean isLocked;


    public ArticleDto(BoardArticle article){
        this.id = article.getId();
        this.type = article.getBoardInfo().getBoardType();
        this.title = article.getArticleTitle();
        this.writer = article.getMemberId().getUserName();
        this.articleAt = getArticleAt();
        this.boardInfo = article.getBoardInfo().getId();
    }

}
