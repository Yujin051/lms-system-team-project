package org.example.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ArticleDto;
import org.example.entity.BoardArticle;
import org.example.repository.BoardInfoRepository;
import org.example.repository.BoardPagingRepository;
import org.example.repository.BoardArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 임승범
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardInfoRepository boardInfoRepository;
    private final BoardArticleRepository boardRepository;
    private final BoardPagingRepository boardPagingRepository;

    // jpa 페이징 리스트 가져오기(게시글 목록)
    public Page<BoardArticle> getArticlesByBoardId(Long boardId , Pageable pageable){

        return boardPagingRepository.findByBoardInfo_Id(boardId , pageable);
    }

    // 게시글 하나 id로 가져오기(상세조회)
    public BoardArticle findById(Long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found : BoardArticle의 " + id + "를 찾을 수 없음."));
    }

    // 새로운 게시글 추가하기(게시글 추가)
    public BoardArticle save(ArticleDto articleDto){

//        log.info("articleDto::{}" , articleDto);
        BoardArticle article = articleDto.toEntity();

        return boardRepository.save(article);
    }

    // 게시글 하나 id로 수정하기(게시글 수정)
    @Transactional
    public BoardArticle update(Long id , ArticleDto articleDto){

        BoardArticle article = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found : BoardArticle의 " + id + "를 찾을 수 없음."));

        log.info("업데이트 하려는 articleDto::{}",articleDto);
        article.update(articleDto);

        return article;

    }








}

