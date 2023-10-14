package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.StudLectProgDto;
import org.example.service.admin.YoutubeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/youtube")
public class YoutubeController {

    private final YoutubeService youtubeService;

    /**
     * 관리자 - 온라인강의콘텐츠관리 : 일시정지 또는 영상이 종료될 때마다
     * 마지막 재생 위치의 시간 디비에 저장
     * @author 임휘재
     */
//    @PutMapping("/api/fnlPosi/save")
//    public ResponseEntity<?> saveLastPlayTime(@RequestParam Long magId,
//                                              @RequestParam int lastPlayTime) {
//        log.info("magId : " + magId);
//        log.info("last : " + lastPlayTime);
////        log.info("principal : " + principal.getName());
//        youtubeService.getSaveLastPlayTime(magId, lastPlayTime);
//        return ResponseEntity.ok("마지막 재생 위치의 시간 저장 성공.");
//    }
    /**
     * 관리자 - 온라인강의콘텐츠관리 : 5초마다 저장
     * @author 임휘재
     */
    @PutMapping("/api/savePlayTime")
    public ResponseEntity<?> savePlayTime(@RequestParam Long magId) {
        log.info("magId: " + magId);
//        log.info("maxPlayTime: " + maxPlayTime);
//        log.info("lastPlayTime: " + lastPlayTime);

        youtubeService.savePlayTime(magId);
        return ResponseEntity.ok("재생 위치 저장 성공.");
    }


}
