package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ArticleDto;
import org.example.entity.BoardArticle;
import org.example.entity.BoardInfo;
import org.example.entity.Member;
import org.example.repository.BoardInfoRepository;
import org.example.service.BoardInfoService;
import org.example.service.BoardService;
import org.example.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardRestController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final BoardInfoService BoardInfoService;

    @PostMapping("/write")
    public ResponseEntity<BoardArticle> addNewBoardArticle(@RequestBody ArticleDto articleDto , Principal principal){

        log.info("Post요청 /board/write{id} >>> addNewBoardArticle() 실행됨.");
        // userId를 이용하여 member 가져오고 dto설정
        Member member = memberService.memberView(principal.getName());
        articleDto.setMember(member);

        // dto에 기록되어 있는 boardId 가져온 걸로 BoardInfo 레코드 찾아오기.
        Long id = articleDto.getType();
        BoardInfo boardInfo = BoardInfoService.findById(id);

        // dto에 넣어주고 BoardArticle로 만들어준다.
        articleDto.setBoardInfo(boardInfo);

        BoardArticle article = boardService.save(articleDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

}
