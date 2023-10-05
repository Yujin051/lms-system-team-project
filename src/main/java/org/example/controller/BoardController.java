package org.example.controller;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.example.dto.board.notice.ArticleListViewDto;
import org.example.entity.BoardArticle;
import org.example.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 리스트 전체 글 보기
    @GetMapping("/notice_list/{id}/{page}")
    public String noticeList(Model model ,
                             @PathVariable(name = "id" , required = false) Long id ,
                             @PathVariable(name = "page" , required = false) int page) {

        // 페이지당 표시할 게시글 갯수
        int pageSize = 10;

        PageRequest pageable = PageRequest.of(page -1 , pageSize);

        Page<BoardArticle> articles = boardService.getArticlesByBoardId(pageable , id);

        model.addAttribute("articles" , articles.getContent());
        model.addAttribute("currentPage" , articles.getNumber() + 1);
        model.addAttribute("totalPages" , articles.getTotalPages());

//        List<ArticleListViewDto> articles = boardService.getArticleByBoardId(id)
//                .stream()
//                .map(ArticleListViewDto::new)
//                .toList();
//        model.addAttribute("articles" , articles);

        return "/community/commu_list";
    }

    // notice 게시글 보기 - DB 연동 필요
    @GetMapping("/notice/article")
    public String noticeView() {
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
