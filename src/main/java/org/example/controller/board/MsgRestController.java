package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ClassMateDto;
import org.example.entity.Member;
import org.example.service.StudLectApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
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

    @PostMapping("/msg/getClassMate")
    public ResponseEntity<?> getClassMate(@RequestBody ClassMateDto classMateDto){

        log.info("ClassMateDto::{}" , classMateDto);

        // 참가자 목록을 모두 가져온다 (강사 , 학생) member로 가져와야 할 것.
        List<ClassMateDto> classmates = studLectApplyService.findClassmate(classMateDto);
        log.info("classmates::{}",classmates);

        return ResponseEntity.status(HttpStatus.OK).body(classmates);

    }

}
