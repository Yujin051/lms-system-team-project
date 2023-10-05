package org.example.repository;

import org.example.entity.BoardArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardPagingRepository extends PagingAndSortingRepository<BoardArticle , Long> {
    Page<BoardArticle> findByBoardId_id(Long boardId , Pageable pageable);
}
