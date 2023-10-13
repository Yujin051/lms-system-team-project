package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.board.CommentDto;
import org.example.entity.BoardComnt;
import org.example.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 임승범
 */
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 저장
    public BoardComnt save(CommentDto commentDto){

        BoardComnt comnt = commentRepository.save(commentDto.toEntity());

        return comnt;

    }

    // 게시글 번호로 소속된 댓글 리스트 가져오기
    public List<BoardComnt> findAll(Long articleId){
        List<BoardComnt> comnts = commentRepository.findByBoardArticle_Id(articleId);
        return comnts;
    }

}
