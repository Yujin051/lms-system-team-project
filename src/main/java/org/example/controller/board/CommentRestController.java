package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.CommentDto;
import org.example.entity.BoardArticle;
import org.example.entity.BoardComnt;
import org.example.entity.Member;
import org.example.service.BoardService;
import org.example.service.CommentService;
import org.example.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * @author 임승범
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentRestController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/write")
    public ResponseEntity<BoardComnt> addCommentArticle(@RequestBody CommentDto commentDto , Principal principal){

        log.info("POST요청 /comment/write >>> addCommentArticle()실행됨.");
//        log.info("commentDto::{}",commentDto);
        // 소속된 게시글 가져오기
        BoardArticle boardArticle =boardService.findById(commentDto.getBoardArticleId());
        // 사용자 정보 가져오기
        Member member = memberService.memberView(principal.getName());

        commentDto.setBoardArticle(boardArticle);
        commentDto.setMember(member);

        BoardComnt comnt = commentService.save(commentDto);

         return ResponseEntity.status(HttpStatus.CREATED).body(comnt);
    }

    // 댓글 수정
    @PutMapping("/modify")
    public ResponseEntity<BoardComnt> updateCommentArticle(@RequestBody CommentDto commentDto){

        log.info("Put요청 /comment/modify >>> updateCommentArticle()실행됨.");
//        log.info("업데이트 내용 commentDto::{}",commentDto);

        BoardComnt comnt = commentService.update(commentDto);
//        log.info("comnt 업데이트 받고 온 상태::{}",comnt);

        return ResponseEntity.status(HttpStatus.CREATED).body(comnt);
    }

    // 댓글 삭제
    @PostMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestBody CommentDto commentDto){

        log.info("Post요청 /comment/delete >>> deleteComment()실행됨.");
//        log.info("commentDto::{}",commentDto);

        BoardComnt comnt = commentService.delete(commentDto);

        if(comnt.getIsDeleted()){
            return ResponseEntity.status(HttpStatus.OK).body("성공");
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
        }

    }


}
