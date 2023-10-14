package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.example.dto.board.ArticleDto;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "board_article")
@Getter
@Entity
@ToString
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BoardArticle {

    // 게시글 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id" , updatable = false)
    private Long Id;

    //  게시판 id (외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id" , updatable = false , nullable = false)
    @JsonIgnore
    private BoardInfo boardInfo;

    // 게시글 이름
    @Column(name = "article_title" , nullable = false)
    private String articleTitle;

    // 게시글 내용 , 값 손실 방지 = varchar말고 text로 설정
    @Column(name = "article_cont" , nullable = false  , columnDefinition = "TEXT")
    private String articleContent;

    // 게시글 조회수
    @Column(name = "article_view" , nullable = false)
    @ColumnDefault("0")
    private Long articleView;

    // 게시글 작성일시
    @CreatedDate
    @Column(name = "article_at" , nullable = false)
    private LocalDateTime articleAt;

    // 게시글 공개여부
    @Column(name = "is_locked" , nullable = false)
    @ColumnDefault("false")
    private Boolean isLocked;

    // 게시글 삭제여부
    @Column(name = "is_deleted" , nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    // 게시글 작성자(외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id" , updatable = false , nullable = false)
    @JsonIgnore
    private Member memberId;

    // 게시글 첨부파일 번호
    @Column(name = "file_num" , nullable = false)
    @ColumnDefault("0")
    private Long articleFileNum;

    // entity 생성
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
    // 수정
    public void update(ArticleDto articleDto){
        this.articleTitle = articleDto.getTitle();
        this.articleContent = articleDto.getContent();
        this.isLocked = articleDto.getIsLocked();
        this.articleFileNum =
                articleDto.getFileNo() != null && articleDto.getFileNo() != 0L && this.articleFileNum == 0L?
                        articleDto.getFileNo() : this.articleFileNum;
    }
    // 논리 삭제
    public void delete(){
        this.isDeleted = true;
    }


}