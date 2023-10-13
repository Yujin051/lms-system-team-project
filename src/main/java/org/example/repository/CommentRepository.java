package org.example.repository;

import org.example.entity.BoardComnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 임승범
 */
@Repository
public interface CommentRepository extends JpaRepository<BoardComnt , Long> {

    // 게시글 번호로 댓글 리스트 가져오기
    public List<BoardComnt> findByBoardArticle_Id(Long BoardarticleId);

}
