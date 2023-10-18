package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.LmsContsDto;
import org.example.entity.LmsConts;
import org.example.service.admin.LecContsService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LmsContsController {

    private final LecContsService lecContsService;

    // 첫 페이지 접근 시 모든 컨텐츠 리스트 그리드에 출력
    @GetMapping("/findAllConts")
    public ResponseEntity<?> findConts() {
        // 전체 리스트 찾기
        JSONObject contsData = lecContsService.findAllContsToJson();

        // 페이지로 반환
        return ResponseEntity.status(HttpStatus.OK).body(contsData.toString());
    }

    // 클릭했을 때 컨텐츠 상세정보 오른쪽 표에 출력하는 컨트롤러
    @GetMapping("/findContsDetail")
    public ResponseEntity<?> findContsDetail(@RequestParam(name = "contsNo") Long contsNo) {
        LmsContsDto lmsContsDto = lecContsService.getDetail(contsNo);
        return ResponseEntity.status(HttpStatus.OK).body(lmsContsDto);
    }
}
