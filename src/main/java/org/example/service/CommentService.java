package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.CommentDto;
import org.example.entity.BoardComnt;
import org.example.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 임승범
 */
@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 저장
    public BoardComnt save(CommentDto commentDto){

        BoardComnt comnt = commentRepository.save(commentDto.toEntity());

        return comnt;

    }

    // 게시글 번호로 소속된 댓글 리스트 가져오기
    public List<BoardComnt> findAll(Long articleId){
        List<BoardComnt> comnts = commentRepository.findByBoardArticle_IdAndIsDeletedFalse(articleId);
        return comnts;
    }

    // 댓글 수정
    public BoardComnt update(CommentDto commentDto){

        log.info("업데이트하려는 dto = " + commentDto.toString());

        BoardComnt comnt = commentRepository.findById(commentDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("Not found : " + commentDto.getId() + " 로 Entity 찾지 못함."));

        log.info("comnt::{}",comnt);

        comnt.update(commentDto.getCommentText());

        return comnt;
    }

    // 댓글 삭제
    public BoardComnt delete(CommentDto commentDto){

        BoardComnt comnt = commentRepository.findById(commentDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("Not found : " + commentDto.getId() + "로 Entity 찾지 못함."));

        comnt.delete();

        return comnt;
    }

}
