package org.example.controller;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ArticleViewDto;
import org.example.dto.board.notice.ArticleListViewDto;
import org.example.entity.BoardArticle;
import org.example.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.reverse;

/**
 * @author 임승범
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 리스트 전체 글 보기
    @GetMapping("/list/{id}")
    public String boardList(
            Model model ,
            @PathVariable(name = "id" , required = false) Long boardId,
            @PageableDefault(page = 0 , size = 10 , sort = "Id" , direction = Sort.Direction.DESC) Pageable pageable) {

        log.info("Get요청 /board/list{id} >>> boardList() 실행됨. ");

        // jpa PagingAndSortRepository 이용
        Page<BoardArticle> articles = boardService.getArticlesByBoardId(boardId , pageable);

        int nowPage = articles.getPageable().getPageNumber() + 1 ;      // 현재 페이지
        int startPage = Math.max(nowPage - 4 , 1);                      // 시작 페이지 (Math.max이용 , 비교 큰 값 할당)
        int endPage = Math.min(nowPage + 5 , articles.getTotalPages()); // 마지막 페이지 (Math.max이용 , 비교 작은 값 할당)
        int totalPage = articles.getTotalPages();   // 페이지 수
        Long total = articles.getTotalElements();   // 게시글 갯수(레코드 수)

        // model에 담기
        model.addAttribute("articles" , articles);
        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("startPage" , startPage);
        model.addAttribute("endPage" , endPage);
        model.addAttribute("total" , total);
        model.addAttribute("id" , boardId);
        model.addAttribute("totalPage" , totalPage);

        return "/community/commu_list";
    }

    // 게시글 상세 조회
    @GetMapping("/view/{id}")
    public String boardView(Model model , @PathVariable(name = "id" , required = false) Long id) {

        log.info("Get요청 /board/view >>> boardView() 실행됨.");

        // id 해당하는 boardArticle 레코드 가져옴.
        BoardArticle article = boardService.findById(id);

        model.addAttribute("article" , article);

        return "/community/article";
    }

    // 쪽지 전체 보기
    @GetMapping("/msg/all")
    public String msgList() {
        return "/community/msg_list";
    }

    // 특정 쪽지 내용 보기
    @GetMapping("/msg/article")
    public String msgView() {
        return "/community/msg_view";
    }

    // 게시판 글 쓰기(아마도 공지사항인것 같음, PostMapping 필요)
    @GetMapping("/notice/article_write")
    public String test5() {
        return "/community/newArticle";
    }

    // 새 쪽지 작성하기(PostMapping 필요)
    @GetMapping("/msg/article_write")
    public String test6() {
        return "/community/newMsg";
    }
}
