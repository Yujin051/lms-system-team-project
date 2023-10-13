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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void addCommentArticle(@RequestBody CommentDto commentDto , Principal principal){

        log.info("commentDto::{}",commentDto);

        BoardArticle boardArticle =boardService.findById(commentDto.getBoardArticleId());
        Member member = memberService.memberView(principal.getName());

        commentDto.setBoardArticle(boardArticle);
        commentDto.setCommentWriter(member.getUserName());



    }


    // 댓글 수정

    // 댓글 삭제


}
