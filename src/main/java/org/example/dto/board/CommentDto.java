package org.example.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.BoardArticle;
import org.example.entity.BoardComnt;
import org.example.entity.Member;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
//  새로운 댓글 요청 DTO
public class CommentDto {

    // 댓글 id
    private Long id;
    // 소속되는 게시글 id v
    private Long boardArticleId;
    // 소속되는 게시글
    private BoardArticle boardArticle;
    // 댓글 작성자 text
    private String commentWriter;
    // 댓글 작성자 id v
    private Long commentWriterId;
    // 댓글 내용 v
    private String commentText;
    // 댓글 작성일시
    private LocalDateTime commentAt;

    public BoardComnt toEntity(){
        return BoardComnt.builder()
                .boardArticle(boardArticle)
                .comntCont(commentText)
                .comntWriter(commentWriter)
                .isDeleted(false)
                .build();
    }


}
