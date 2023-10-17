package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.RoleType;
import org.example.dto.board.DirectMsgDto;
import org.example.dto.board.MsgPageDto;
import org.example.dto.board.PageDto;
import org.example.entity.DirectMsg;
import org.example.entity.Member;
import org.example.entity.StudLectApply;
import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.example.service.DirectMsgService;
import org.example.service.MemberService;
import org.example.service.StudLectApplyService;
import org.example.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * @author 임승범
 */

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class MsgController {

    private final StudLectApplyService studLectApplyService;
    private final MemberService memberService;
    private final StudentService studentService;
    private final DirectMsgService directMsgService;

    // 쪽지 전체 보기
    @GetMapping("/msg/all")
    public String getMsgList(
            Model model ,
            @PageableDefault(page = 0 , size = 5 , sort = "id" , direction = Sort.Direction.DESC) Pageable pageable ,
            Principal principal) {

        Page<DirectMsg> msgPages = directMsgService.getAllMsg(principal , pageable);
        MsgPageDto msgPageDto = new MsgPageDto(msgPages);

        Member member = memberService.memberView(principal.getName());

        model.addAttribute("pageDto" , msgPageDto);
        model.addAttribute("member" , member);

        return "/community/msg_list";
    }

    // 특정 쪽지 내용 보기
    @GetMapping("/msg/view/{id}")
    public String getMsgView(@PathVariable(name = "id" , required = false)Long id) {

        return "/community/msg_view";
    }

    // 새 쪽지 작성하기
    @GetMapping("/msg/article_write")
    public String writeMsg(
            Model model ,
            DirectMsgDto directMsgDto ,
            Principal principal) {

        log.info("Get요청 /board/msg/article_write >>> writeMsg()실행됨.");

        Member member = memberService.memberView(principal.getName());
        directMsgDto.setSendId(member); // 발신자 설정

        if(member.getUserRole().getTitle().equals("고객")){
            Student student = studentService.findByMember(member);
            List<StudLectApply> classList = studLectApplyService.getStudLectApply(student);
//            log.info("classList::{}",classList);
            // 수강리스트 모델에 추가
            model.addAttribute("classList" , classList);
        }
        else if(member.getUserRole().getTitle().equals("강사")){

        }
        else if(member.getUserRole().getTitle().equals("관리자")){

        }

        model.addAttribute("msg" , directMsgDto);

        return "/community/newMsg";
    }





}
