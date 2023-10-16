package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ClassMateDto;
import org.example.service.StudLectApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;

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

//        studLectApplyService.get

        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }

}
