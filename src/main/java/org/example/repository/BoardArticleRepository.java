package org.example.repository;

import org.example.entity.BoardArticle;
import org.example.entity.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardArticleRepository extends JpaRepository<BoardArticle , Long> {

    // 게시글 리스트 내용 검색
    List<BoardArticle> searchByArticleContent(String searchValue);
    // 게시글 리스트 제목 검색
    List<BoardArticle> searchByArticleTitle(String searchValue);
    // 게시글 리스트 작성자(실명) 검색
    List<BoardArticle> searchByMemberId_UserName(String searchValue);
}
