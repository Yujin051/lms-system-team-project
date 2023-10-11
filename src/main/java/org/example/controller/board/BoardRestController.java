package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ArticleDto;
import org.example.dto.board.SearchDto;
import org.example.entity.BoardArticle;
import org.example.entity.BoardInfo;
import org.example.entity.Member;
import org.example.repository.BoardInfoRepository;
import org.example.service.BoardInfoService;
import org.example.service.BoardService;
import org.example.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardRestController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final BoardInfoService BoardInfoService;

    // 게시글 쓰기
    @PostMapping("/write")
    public ResponseEntity<BoardArticle> addNewBoardArticle(@RequestBody ArticleDto articleDto , Principal principal){

        log.info("Post요청 /board/write >>> addNewBoardArticle() 실행됨.");
        // userId를 이용하여 member 가져오고 dto설정
        Member member = memberService.memberView(principal.getName());
        articleDto.setMember(member);
        // dto에 기록되어 있는 boardId 가져온 걸로 BoardInfo 레코드 찾아오기.
        Long id = articleDto.getType();
        BoardInfo boardInfo = BoardInfoService.findById(id);
        // dto에 넣어주고 BoardArticle로 만들어준다.
        articleDto.setBoardInfo(boardInfo);
        // dto에 fileInfo 넣어주기

        BoardArticle article = boardService.save(articleDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    // 게시글 하나 수정
    @PutMapping("/modify")
    public BoardArticle updateBoardArticle(@RequestBody ArticleDto articleDto){

        log.info("Post요청 /board/modify >>> updateBoardArticle() 실행됨. ");
        BoardArticle article = boardService.update(articleDto.getId() , articleDto);

        return article;
    }

    // 게시글 삭제
    @PostMapping("/deleted")
    public ResponseEntity<String> deleteBoardArticle(@RequestBody Map<String,Long> articleId){
        Long id = articleId.get("articleId");

        log.info("Post요청 /board/deleted/{id} >>> deleteBoardArticle()실행됨.");
        BoardArticle article = boardService.delete(id);

        if(article.getIsDeleted() == true){
            return ResponseEntity.status(HttpStatus.OK).body("삭제되었습니다.");
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제처리 실패.");
        }

    }

    // 게시글 검색
    @PostMapping("/search")
    public void searchList(
            @RequestBody SearchDto searchDto ,
            @PageableDefault(page = 0 , size = 10 , sort = "Id" , direction = Sort.Direction.DESC)Pageable pageable){

        log.info("Post요청 /board/search >>> searchList() 실행됨.");
        log.info("searchDto::{}",searchDto);

        String type = searchDto.getSearchType();

        if(type == "title"){ // 게시글 제목

        }
        else if(type == "content"){ // 게시글 내용

        }
        else if(type == "writer"){ // 게시글 작성자

        }
        else{
            return ;
        }
    }

}
