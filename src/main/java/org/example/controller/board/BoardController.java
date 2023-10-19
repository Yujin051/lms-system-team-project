package org.example.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class BoardController {



    // notice 전체 글 보기
    @GetMapping("/notice")
    public String noticeList() {
        return "/community/commu_list";
    }

    // notice 게시글 보기 - DB 연동 필요
    @GetMapping("/notice/noticenum변경필요")
    public String noticeView() {
        return "/community/article";
    }

    // 쪽지 전체 보기
    @GetMapping("/msglist")
    public String msgList() {
        return "/community/msg_list";
    }

    // 특정 쪽지 내용 보기
    @GetMapping("/msglist/msgnum변경필요")
    public String msgView() {
        return "/community/msg_view";
    }

    // 게시판 글 쓰기(아마도 공지사항인것 같음, PostMapping 필요)
    @GetMapping("/newarticle")
    public String test5() {
        return "/community/newArticle";
    }

    // 새 쪽지 작성하기(PostMapping 필요)
    @GetMapping("/newmsg")
    public String test6() {
        return "/community/newMsg";
    }
}
