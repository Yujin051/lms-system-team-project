package org.example.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.BoardArticle;
import org.example.entity.BoardInfo;
import org.example.entity.Member;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
// 새로운 게시글 요청 DTO
public class ArticleDto {

    // 게시글 id
    private Long id;
    // 게시판 종류 id
    private Long type;
    // 게시글 제목
    private String title;
    // 게시글 작성자 이름
    private String writer;
    // 게시글 작성자 id
    private Long memberId;
    // 게시글 멤버
    private Member member;
    // 게시글 작성 시간
    private LocalDateTime articleAt;
    // 게시글 내용
    private String content;
    // 게시글 공개여부
    private Boolean isLocked;

    private BoardInfo boardInfo;


    public BoardArticle toEntity(){
        return BoardArticle.builder()
                .articleTitle(title)
                .articleContent(content)
                .isLocked(isLocked)
                .boardInfo(boardInfo)
                .memberId(member)
                .articleView(0L)
                .articleFileNum(0L)
                .isDeleted(false)
                .build();
    }

}
