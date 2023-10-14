package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.board.ArticleDto;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author 임승범
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "board_comnt")
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
@Entity
public class BoardComnt {

    // 댓글 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comnt_id" , updatable = false)
    private Long Id;

    //  게시글(article) id (외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id" , updatable = false , nullable = false)
    private BoardArticle boardArticle;

    // 댓글 작성일시
    @CreatedDate
    @Column(name = "comnt_at" , nullable = false)
    private LocalDateTime comntAt;

    // 댓글 작성자
    @Column(name = "comnt_writer" , nullable = false)
    private String comntWriter;

    // 댓글 내용
    @Column(name = "comnt_cont" , nullable = false)
    private String comntCont;

    // 댓글 논리삭제
    @Column(name = "isDeleted" , nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    // entity 생성
    @Builder
    public BoardComnt (BoardArticle boardArticle , String comntWriter , String comntCont , Boolean isDeleted){
        this.boardArticle = boardArticle;
        this.comntWriter = comntWriter;
        this.comntCont = comntCont;
        this.isDeleted = isDeleted;
    }
    // 내용 수정
    public void update(ArticleDto articleDto){
        this.comntCont = articleDto.getContent();
    }
    // 논리 삭제
    public void delete(){
        this.isDeleted = true;
    }

}
