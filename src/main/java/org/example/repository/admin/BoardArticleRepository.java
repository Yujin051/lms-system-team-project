package org.example.repository.admin;

import org.example.entity.BoardArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardArticleRepository extends JpaRepository<BoardArticle, Long> {

}
