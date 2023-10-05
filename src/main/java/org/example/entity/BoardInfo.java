package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

/**
 * @author 임승범
 */
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_info")
@Getter
@Entity
@ToString
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
    private String boardType;

}
