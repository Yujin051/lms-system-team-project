package org.example.repository;

import org.example.entity.BoardArticle;
import org.example.entity.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardArticleRepository extends JpaRepository<BoardArticle , Long> {

}
