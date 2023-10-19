package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.StudLectProgDto;
import org.example.service.admin.YoutubeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.api.services.youtube.model.Video;
import org.example.dto.admin.LmsContsDto;
import org.example.entity.LmsConts;
import org.example.repository.LmsContsRepository;
import org.example.service.admin.LmsContsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/youtube")
public class YoutubeController {

    private final YoutubeService youtubeService;
    private final LmsContsRepository lmsContsRepository;
    private final LmsContsService lmsContsService;

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
        log.info("최대재생시간 : " + dto.getMaxPosi());
        return dto.getMaxPosi();
    }

    /**
     * 관리자 - 온라인강의콘텐츠관리 : 최종 재생 시간 조회
     * @author 임휘재
     */
    @GetMapping("/api/getFnlPosi")
    public double findFnlPosi() {
        StudLectProgDto dto = youtubeService.getFindMagId();
        log.info("최종재생시간 : " + dto.getFnlPosi());
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

    @PutMapping("/api/saveProgress")
    public double saveProgress(@RequestParam(value = "magId") Long magId,
                               @RequestParam(value = "progress") double progress) {
        log.info("saveProgressMagId : {}", magId);
        log.info("saveProgress : {}", progress);
        return youtubeService.saveProgress(magId, progress);
    }

    // 업로드 로직
    @PostMapping("/vidUpload")
    public ResponseEntity<?> upload(@RequestParam(name = "file") MultipartFile file,
                                    @RequestParam(name = "title") String title,
                                    @RequestParam(name = "detail") String detail) throws IOException {

        // API 통해 비디오 업로드, 업로드된 정보를 가진 video 객체 반환받음
        Video returnedVideo = youtubeService.uploadVideo(title, detail, file);

        // 이름, 설명, 유튜브 비디오 ID로 DB에 새 컨텐츠 저장
        LmsConts lmsConts = LmsConts.builder()
                .contsName(title)
                .contsDetail(detail)
                .contsYout(returnedVideo.getId())
                .build();
        lmsContsRepository.save(lmsConts);

        // 신규 저장 후 그리드 리셋을 위한 데이터 전송 서비스
        List<LmsContsDto> lmsContsDtoList = lmsContsService.findAllConts();
        return ResponseEntity.status(HttpStatus.OK).body(lmsContsDtoList);
    }

    // 저장 버튼 동작 컨트롤러
    @PostMapping("/vidUpdate")
    public ResponseEntity<?> update(@RequestParam(name = "contsNo") Long contsNo,
                                    @RequestParam(name = "videoId") String id,
                                    @RequestParam(name = "title") String newTtile,
                                    @RequestParam(name = "detail") String newDetail) throws IOException {

        // 비디오 정보 업데이트 서비스
        youtubeService.updateVideo(id, newTtile, newDetail);
        // DB에 정보 업데이트
        lmsContsService.updateConts(contsNo, newTtile, newDetail);

        // 업데이트 후 그리드 갱신
        List<LmsContsDto> lmsContsDtoList = lmsContsService.findAllConts();
        return ResponseEntity.status(HttpStatus.OK).body(lmsContsDtoList);
    }

    // 초 갱신 컨트롤러
    @PostMapping("/getDuration")
    public ResponseEntity<?> getDuration(@RequestParam(name = "videoId") String id) throws IOException {

        // 비디오 ID로 유튜브에서 시간 초 얻어오기
        int duration = youtubeService.getVideoTime(id);
        // 얻어온 초로 업데이트
        lmsContsService.updateTime(id, duration);

        // 그리드 갱신
        List<LmsContsDto> lmsContsDtoList = lmsContsService.findAllConts();
        return ResponseEntity.status(HttpStatus.OK).body(lmsContsDtoList);
    }

    // 삭제 컨트롤러
    @DeleteMapping("/deleteVid")
    public ResponseEntity<?> deleteVideo(@RequestParam(name = "videoId") String videoId) throws IOException {

        // 비디오 아이디로 유튜브 컨텐츠 삭제
        youtubeService.deleteVideo(videoId);
        // 비디오 아이디로 DB 컨텐츠 삭제
        lmsContsService.deleteConts(videoId);

        return ResponseEntity.status(HttpStatus.OK).body("삭제가 정상적으로 완료되었습니다.");
    }

}
