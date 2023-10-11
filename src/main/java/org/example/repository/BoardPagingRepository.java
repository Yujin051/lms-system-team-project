package org.example.repository;

import org.example.entity.BoardArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPagingRepository extends PagingAndSortingRepository<BoardArticle , Long> {
    Page<BoardArticle> findByBoardInfo_IdAndIsDeletedFalse(Long boardId , Pageable pageable);

    // 게시글 리스트 내용 검색
    @Query("SELECT ba FROM BoardArticle ba WHERE ba.articleContent LIKE %:searchValue%")
    Page<BoardArticle> searchByArticleContent(@Param("searchValue")String searchValue , Pageable pageable);

//    select * from boardArticle where articlecontent like %value%;

    // 게시글 리스트 제목 검색
    Page<BoardArticle> searchByArticleTitle(String searchValue , Pageable pageable);

    // 게시글 리스트 작성자(실명) 검색
    Page<BoardArticle> searchByMemberId_UserName(String searchValue , Pageable pageable);

}
