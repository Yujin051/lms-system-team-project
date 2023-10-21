package org.example.controller.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.RoleType;
import org.example.dto.board.ArticleDto;
import org.example.dto.board.PageDto;
import org.example.entity.BoardArticle;
import org.example.entity.BoardComnt;
import org.example.entity.FileInfo;
import org.example.entity.Member;
import org.example.service.BoardService;
import org.example.service.CommentService;
import org.example.service.FileInfoService;
import org.example.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 임승범
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final FileInfoService fileInfoService;
    private final CommentService commentService;

    // 리스트 전체 글 보기
    @GetMapping("/list/{id}")
    public String getBoardList(
            Model model ,
            @PathVariable(name = "id") Long boardId,
            @PageableDefault(page = 0 , size = 10 , sort = "Id" , direction = Sort.Direction.DESC) Pageable pageable ,
            Principal principal ,
            HttpSession httpSession) {

        log.info("Get요청 /board/list/{" + boardId + "} >>> getBoardList() 실행됨. ");

        // principal로 사용자 권한 가져오기
        Member member = memberService.memberView(principal.getName());
        RoleType roleType = member.getUserRole();

        if(httpSession.getAttribute("viewedArticleIds") == null){
            httpSession.setAttribute("viewedArticleIds" , new ArrayList<>());
        }

        // jpa PagingAndSortRepository 이용
        Page<BoardArticle> articles = boardService.getArticlesByBoardId(boardId , pageable);
        PageDto pageDto = new PageDto(articles);

        // model에 담기
        model.addAttribute("pageDto" , pageDto);
        model.addAttribute("id" , boardId);
        model.addAttribute("member" , member);
        model.addAttribute("roleType" , roleType);  // USER , TEACHER , ADMIN
        model.addAttribute("userName" , principal.getName());

        return "/community/commu_list";
    }

    // 게시글 상세 조회
    @GetMapping("/view/{id}")
    public String getBoardView(
            Model model ,
            @PathVariable(name = "id" , required = false) Long id,
            Principal principal,
            Pageable pageable ,
            HttpSession httpSession) {

        log.info("Get요청 /board/view/{" + id + "} >>> getboardView() 실행됨.");

        // 게시글 가져오면서 조회수 증가로직 작동
        BoardArticle article = boardService.updateView(id , httpSession);

        List<FileInfo> files = new ArrayList<>();
        Long fileNo = article.getArticleFileNum(); // 첨부파일 번호

        if(fileNo != null && fileNo != 0L){
            files = fileInfoService.findFileInfoList(fileNo);
//            log.info("files::{}",files);
        }
        // 이전 게시글
        BoardArticle beforeArticle = boardService.findBeforeBoardArticle(
                article.getId() , article.getBoardInfo().getId() , pageable);
        if(beforeArticle != null){
            model.addAttribute("beforeArticle" , beforeArticle);
        }
        // 다음 게시글
        BoardArticle nextArticle = boardService.findNextBoardArticle(
                article.getId(), article.getBoardInfo().getId() , pageable);
        if(nextArticle != null){
            model.addAttribute("nextArticle" , nextArticle);
        }
        // 댓글 목록 가져오기
        List<BoardComnt> comnts = commentService.findAll(article.getId());
        if(comnts != null){
            model.addAttribute("comnts" , comnts);
        }

        // member 가져오기
        Member member = memberService.memberView(principal.getName());
        model.addAttribute("files" , files);
        model.addAttribute("member" , member);
        model.addAttribute("article" , article);

        return "/community/article";
    }

    // 게시판 글 쓰기
    @GetMapping("/write/{type}")
    // type = 게시판 종류 , id는 게시글 번호인데 있을수도 없을 수도 있음.
    public String addBoardArticle( @PathVariable(name = "type") Long type ,
                                   @RequestParam(name = "id" , required = false) Long id ,
                                   Model model,
                                   Principal principal) throws Exception {
        // username = userId
        log.info("*************************************************************** principal::{}",principal);
        log.info("Get요청 >>> /board/write/{" + type + "} >>> addBoardArticle() 실행됨.");
        log.info("*************************************************************** type = " + type);

        // 사용자 회원 정보 가져오기.(principal의 userName은 userId에 해당. unique)
        Member member = memberService.memberView(principal.getName());
        // dto 생성
        ArticleDto articleDto = new ArticleDto();
        // 작성자 이름 user_name 할당
        articleDto.setWriter(member.getUserId());
        // 작성자 id(pk) 설정
        articleDto.setMemberId(member.getId());
        // 게시판 종류 board_id 할당
        articleDto.setType(type);

        if(id == null){
            model.addAttribute("article" ,articleDto);
//            log.info("in null로 실행됨.");
        }
        else{
            // 게시글 정보 가져오기
            BoardArticle boardArticle = boardService.findById(id);
//            log.info("boardArticle::{}",boardArticle);
            // 게시글 id 넣어주기
            articleDto.setId(id);
            // 게시글 제목 넣어주기
            articleDto.setTitle(boardArticle.getArticleTitle());
            // 게시글 내용 넣어주기
            articleDto.setContent(boardArticle.getArticleContent());
            // 게시글 비공개 설정 넣어주기
            articleDto.setIsLocked(boardArticle.getIsLocked());

            model.addAttribute("article" , articleDto);
        }
        return "/community/newArticle";
    }

    // 게시글 검색
    @GetMapping("/search/{searchType}/{id}")
    public String searchList(
            Model model,
            @PathVariable(name = "searchType") String searchType,
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "searchValue") String value,
            @PageableDefault(page = 0 , size = 10 , sort = "Id" , direction = Sort.Direction.DESC)Pageable pageable,
            Principal principal){

        log.info("Get요청 /board/search/{" + searchType + "}/{" + id + "}?" + value + " >>> searchList() 실행됨.");

        Page<BoardArticle> articles = null;

        if(searchType.equals("title")){
            articles = boardService.searchByTitle(value , id , pageable);
        }
        else if(searchType.equals("content")){
            articles = boardService.searchByContent(value , id , pageable);
        }
        else if(searchType.equals("writer")){
//            log.info("<< writer 검색 >>");
            articles = boardService.searchByWriter(value , id , pageable);
        }

        if(articles != null){
            PageDto pageDto = new PageDto(articles);
//            log.info("pageDto::{}" , pageDto);

            // principal로 사용자 권한 가져오기
            Member member = memberService.memberView(principal.getName());
            RoleType roleType = member.getUserRole();

            // model에 담기
            model.addAttribute("searchType" , searchType);
            model.addAttribute("searchValue" , value);
            model.addAttribute("pageDto" , pageDto);
            model.addAttribute("id" , id);
            model.addAttribute("roleType" , roleType);  // USER , TEACHER , ADMIN
            model.addAttribute("userName" , principal.getName());
        }

        return "/community/search_list";
    }

}
