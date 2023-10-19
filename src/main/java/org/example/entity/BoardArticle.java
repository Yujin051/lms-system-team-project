package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "board_article")
@Getter
@Setter //수정
@Entity
@ToString
public class BoardArticle {

    // 게시글 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id" , updatable = false)
    private Long Id;

    // 게시판 id (외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id" , updatable = false , nullable = false)
    private BoardInfo boardInfo;

    // 게시글 이름
    @Column(name = "article_title" , nullable = false)
    private String articleTitle;

    // 게시글 내용 , 값 손실 방지 = varchar말고 text로 설정
    @Column(name = "article_cont" , nullable = false  , columnDefinition = "TEXT")
    private String articleContent;

    // 게시글 조회수
    @Column(name = "article_view")
    @ColumnDefault("0")
    private Long articleView;

    // 게시글 작성일시
    @CreatedDate
    @Column(name = "article_at" , nullable = false)
    private LocalDate articleAt;

    // 게시글 공개여부
    @Column(name = "is_locked")
    @ColumnDefault("false")
    private Boolean isLocked;

    // 게시글 삭제여부
    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    // 게시글 작성자(외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id" , updatable = false , nullable = false)
//    @ColumnDefault("3")
    private Member memberId;

    // 게시글 첨부파일 번호
    @Column(name = "file_num")
    @ColumnDefault("0")
    private Long articleFileNum;

    @Builder
    public BoardArticle(
            BoardInfo boardInfo , String articleTitle , String articleContent , Long articleView,
            Boolean isLocked , Boolean isDeleted , Member memberId , Long articleFileNum){
        this.boardInfo = boardInfo;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleView = articleView;
        this.isLocked = isLocked;
        this.isDeleted = isDeleted;
        this.memberId = memberId;
        this.articleFileNum = articleFileNum;
    }
    public BoardArticle(BoardInfo boardInfo, String articleTitle,
                        String articleContent, Long articleView,
                        LocalDate articleAt, Boolean isLocked,
                        Boolean isDeleted, Member memberId, Long articleFileNum) {
        this.boardInfo = boardInfo;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleView = articleView;
        this.articleAt = articleAt;
        this.isLocked = isLocked;
        this.isDeleted = isDeleted;
        this.memberId = memberId;
        this.articleFileNum = articleFileNum;
    }

}