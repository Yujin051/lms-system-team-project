package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.entity.BoardArticle;
import org.example.repository.BoardPagingRepository;
import org.example.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 임승범
 */
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardPagingRepository boardPagingRepository;

    // 외래키 boardInfo의 id값으로 테이블 리스트 가져오기
//    public List<BoardArticle> getArticleByBoardId(Long boardId){
//        return boardRepository.findByBoardInfo_Id(boardId);
//    }

    public Page<BoardArticle> getArticlesByBoardId(Pageable pageable , Long boardId){

        return boardPagingRepository.findByBoardId_id(boardId , pageable);
    }





}

