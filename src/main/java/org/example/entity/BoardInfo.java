package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

/**
 * @author 임승범
 */
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_info")
@Getter @Setter
@Builder
@Entity
@ToString
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BoardInfo {

    // 게시판 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id" , updatable = false)
    private Long Id;

    // 게시판 이름
    @Column(name = "board_name" , nullable = false)
    private String boardName;

    // 게시판 응답여부
    @Column(name = "board_resp" , nullable = false)
    @ColumnDefault("false")
    private Boolean boardResp;

    // 게시판 종류
    @Column(name = "board_type" , nullable = false)
    @JsonIgnore
    private String boardType;

    // 강좌정보
    @OneToOne
    @JoinColumn(name = "lect_info" , nullable = true)
    private LectInfo lectInfo;

    public BoardInfo(String boardName, String boardType, Boolean boardResp){
        this.boardName = boardName;
        this.boardResp = boardResp;
        this.boardType = boardType;
    }

}