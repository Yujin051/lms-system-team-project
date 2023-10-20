package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Gender;
import org.example.entity.BoardArticle;
import org.example.entity.BoardInfo;
import org.example.entity.Member;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class PostDto {

    //member
    private Long id;
    private String userName;
    private String userId;
    private LocalDate userBirthday;
    private Gender userGender;
    private String userEmail;
    private Member member;

    //student
    private Long studId;
    private Long studGrade; //학년

    //board_article
    private Long articleId; //게시글번호
    private String articleTitle; //제목
    private LocalDate articleAt; //작성일자
    private Long articleView; //조회수
    private Boolean isLocked; //공개여부
    private Boolean isDeleted; //삭제여부
    private String articleContent; //게시내용

    //board_info
    private Long boardId; //게시판번호
    private String boardType; //게시판종류
    private String boardName; //게시판이름
    private BoardInfo boardInfo;

    //게시글 목록
    public PostDto(String userName, String userId, LocalDate userBirthday,
                   Gender userGender, String userEmail, Long articleId,
                   String articleTitle, LocalDate articleAt, Long articleView,
                   Boolean isLocked, Boolean isDeleted, String articleContent,
                   String boardType, Long boardId) {
        this.userName = userName;
        this.userId = userId;
        this.userBirthday = userBirthday;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleAt = articleAt;
        this.articleView = articleView;
        this.isLocked = isLocked;
        this.isDeleted = isDeleted;
        this.articleContent = articleContent;
        this.boardType = boardType;
        this.boardId = boardId;
    }

    //게시글 내용
    public PostDto(String userName, Long articleId, String articleTitle,
                   LocalDate articleAt, Long articleView, Boolean isLocked,
                   Boolean isDeleted, String articleContent, String boardType,
                   Long boardId) {
        this.userName = userName;
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleAt = articleAt;
        this.articleView = articleView;
        this.isLocked = isLocked;
        this.isDeleted = isDeleted;
        this.articleContent = articleContent;
        this.boardType = boardType;
        this.boardId = boardId;
    }

    // 게시글 등록
    public BoardArticle toEntity() {
        log.info("boardInfo : " + boardInfo);
        return BoardArticle.builder()
                .articleTitle(articleTitle)
                .memberId(member)
                .boardInfo(boardInfo)
                .isLocked(isLocked)
                .isDeleted(isDeleted)
                .articleContent(articleContent)
                .build();
    }

    //게시판 정보관리 : 게시판 정보목록
    public PostDto(Long boardId, String boardType, String boardName) {
        this.boardId = boardId;
        this.boardType = boardType;
        this.boardName = boardName;
    }

    //게시판 정보관리 : 게시판 등록
    public BoardInfo boardInfoToEntity(){
        return BoardInfo.builder()
                .boardName(boardName)
                .boardType(boardType)
                .build();
    }
}
