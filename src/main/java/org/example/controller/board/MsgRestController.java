package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ClassMateDto;
import org.example.dto.board.DirectMsgDto;
import org.example.entity.DirectMsg;
import org.example.entity.Member;
import org.example.service.DirectMsgService;
import org.example.service.StudLectApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.security.Principal;
import java.util.List;

/**
 * @author 임승범
 */

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/board")
public class MsgRestController {

    private final StudLectApplyService studLectApplyService;
    private final DirectMsgService directMsgService;

    // 수업 참가자 목록 가져오기
    @PostMapping("/msg/getClassMate")
    public ResponseEntity<?> getClassMate(@RequestBody ClassMateDto classMateDto){
        log.info("Post요청 /board/msg/getClassMate >>> getClassMate() 실행됨.");

        // 참가자 목록을 모두 가져온다 (강사 , 학생) member로 가져와야 할 것.
        List<ClassMateDto> classmates = studLectApplyService.findClassmate(classMateDto);
//        log.info("classmates::{}",classmates);

        return ResponseEntity.status(HttpStatus.OK).body(classmates);
    }

    // 메시지 작성
    @PostMapping("/msg/write")
    public ResponseEntity<?> sendMsg(@RequestBody DirectMsgDto directMsgDto , Principal principal){
        log.info("Post요청 /board/msg/article_write >>> sendMsg()실행됨.");
        log.info("directMsgDto::{}" , directMsgDto);
        // 받는사람의 숫자가 1보다 많다면
        if(directMsgDto.getToSome().size() > 1){
            List<DirectMsg> msgs = directMsgService.saveAll(directMsgDto , principal);
//            log.info("msgs::{}",msgs);
            return ResponseEntity.status(HttpStatus.CREATED).body(msgs);
        }
        // 받는 사람이 한명인 경우
        else{
            DirectMsg msg = directMsgService.save(directMsgDto , principal);
            log.info("msg::{}",msg);
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);
        }
    }

    // 메시지 삭제
    @PostMapping("/msg/delete")
    public ResponseEntity<String> deleteMsg(@RequestBody Long id , Principal principal){
        log.info("Post요청 /board/msg/delete >>> deleteMsg()실행됨.");

        Boolean flag = directMsgService.deleteMsg(id , principal);

        if(flag){
            return ResponseEntity.status(HttpStatus.OK).body("삭제 처리 완료.");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 처리 실패");
        }
    }

}
