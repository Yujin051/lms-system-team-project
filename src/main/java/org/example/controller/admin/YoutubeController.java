package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.StudLectProgDto;
import org.example.service.admin.YoutubeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 관리자 - 온라인강의콘텐츠관리 : 컬럼 모두 조회
     * @author 임휘재
     */
    @GetMapping("/api/studLectProg")
    public List<StudLectProgDto> adminGradeStudInfo() {
        List<StudLectProgDto> dtos = youtubeService.getFindStudLectProg();
        return dtos;
    }

    /**
     * 관리자 - 온라인강의콘텐츠관리 : 최대 재생 시간 조회
     * @author 임휘재
     */
    @GetMapping("/api/getMaxPosi")
    public double findMaxPosi() {
        StudLectProgDto dto = youtubeService.getFindMagId();
        log.info("ConmaxPosi : " + dto.getMaxPosi());
        return dto.getMaxPosi();
    }

    /**
     * 관리자 - 온라인강의콘텐츠관리 : 최종 재생 시간 조회
     * @author 임휘재
     */
    @GetMapping("/api/getFnlPosi")
    public double findFnlPosi() {
        StudLectProgDto dto = youtubeService.getFindMagId();
        log.info("ConfnlPosi : " + dto.getFnlPosi());
        return dto.getFnlPosi();
    }

    /**
     * 관리자 - 온라인강의콘텐츠관리 : 5초마다 저장
     * @author 임휘재
     */
    @PutMapping("/api/savePlayTime")
    public ResponseEntity<?> savePlayTime(@RequestParam Long magId,
                                          @RequestParam double fnlPosi,
                                          @RequestParam double maxPosi) {
        log.info("savePlayTime magId: " + magId);
        log.info("fnlPosi : " + fnlPosi);
        log.info("maxPosi : " + maxPosi);

        youtubeService.savePlayTime(magId, fnlPosi, maxPosi);
        return ResponseEntity.ok("재생 위치 저장 성공.");
    }

    /**
     * 관리자 - 온라인강의콘텐츠관리 : fnl만 업데이트
     * @author 임휘재
     */
    @PutMapping("/api/saveFnlPosi")
    public ResponseEntity<?> saveFnlPosi(@RequestParam Long magId,
                                          @RequestParam double fnlPosi) {
        log.info("magId: " + magId);
        log.info("fnlPosi : " + fnlPosi);

        youtubeService.saveFnlPosi(magId, fnlPosi);
        return ResponseEntity.ok("최종 재생 위치 저장 성공.");
    }

    /**
     * 관리자 - 온라인강의콘텐츠관리 : max만 업데이트
     * @author 임휘재
     */
    @PutMapping("/api/saveMaxPosi")
    public ResponseEntity<?> saveMaxPosi(@RequestParam Long magId,
                                         @RequestParam double maxPosi) {
        log.info("magId: " + magId);
        log.info("fnlPosi : " + maxPosi);

        youtubeService.saveMaxPosi(magId, maxPosi);
        return ResponseEntity.ok("최종 재생 위치 저장 성공.");
    }

}
