package org.example.dto.board.notice;

import lombok.Getter;
import org.example.entity.BoardArticle;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@Getter
// 게시글 리스트 출력 DTO
public class ArticleListViewDto {

    private final Long id;
    private final String type;
    private final String title;
    private final String writer;
    private final Long viewNum;
    private final LocalDateTime articleAt;

    public ArticleListViewDto(BoardArticle article){
        this.id = article.getId();
        this.type = article.getBoardInfo().getBoardType();
//                article.getBoardInfo().getBoardType();
        this.title = article.getArticleTitle();
        this.writer = article.getArticleWriter();
        this.viewNum = article.getArticleView();
        this.articleAt = getArticleAt();
    }

}
