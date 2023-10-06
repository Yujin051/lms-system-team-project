package org.example.dto.board;

import lombok.Getter;
import lombok.ToString;
import org.example.entity.BoardArticle;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@ToString
@Getter
public class ArticleViewDto {

    // 게시글 id
    private final Long id;
    // 게시판 종류
    private final String type;
    // 게시글 제목
    private final String title;
    // 게시글 내용
    private final String content;
    // 게시글 조회수
    private final Long viewNum;
    // 게시글 작성일
    private final LocalDateTime articleAt;

    public ArticleViewDto(BoardArticle article) {
        this.id = article.getId();
        this.type = article.getBoardInfo().getBoardType();
        this.title = article.getArticleTitle();
        this.content = article.getArticleContent();
        this.articleAt = article.getArticleAt();
        this.viewNum = article.getArticleView();
    }
}
