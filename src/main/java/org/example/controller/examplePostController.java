package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LectNthDto;
import org.example.service.LectNthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class examplePostController {

    private final LectNthService lectNthService;

    @PostMapping("/api/post")
    public ResponseEntity<String> doPost(@RequestBody String searchValue){
        log.info("post요청 /api/post >>> doPost()실행됨.");
        log.info("searchValue::{}",searchValue);
        return ResponseEntity.status(HttpStatus.OK).body(searchValue);
    }

    @GetMapping("/api/get")
    public ResponseEntity<String> doGet() {
        return ResponseEntity.ok("GET 요청 처리 완료");
    }


}
