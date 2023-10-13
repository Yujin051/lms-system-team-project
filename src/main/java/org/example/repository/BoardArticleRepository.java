package org.example.repository;

import org.example.entity.BoardArticle;
import org.example.entity.BoardInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 임승범
 */

@Repository
public interface BoardArticleRepository extends JpaRepository<BoardArticle , Long> {


     // 다음 게시글 가져오기
    @Query("SELECT ba FROM BoardArticle ba WHERE ba.Id > :nowArticleId AND ba.boardInfo.Id = :boardId ORDER BY ba.Id ASC")
     Page<BoardArticle> findNextBoardArticle(@Param("nowArticleId")Long nowArticleId , @Param("boardId")Long boardId , Pageable pageable);

    // 이전 게시글 가져오기
    @Query("SELECT ba FROM BoardArticle ba WHERE ba.Id < :nowArticleId AND ba.boardInfo.Id = :boardId ORDER BY ba.Id DESC")
    Page<BoardArticle> findBeforeBoardArticle(@Param("nowArticleId")Long nowArticleId , @Param("boardId") Long boardId , Pageable pageable);


}
