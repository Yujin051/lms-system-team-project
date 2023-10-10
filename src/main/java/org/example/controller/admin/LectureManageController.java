package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.dto.admin.LectureListDto;
import org.example.repository.LectureRepository;
import org.example.service.admin.LectureService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureManageController {

    private static final Logger logger = LoggerFactory.getLogger(LectureManageController.class);
    private final LectureService lectureService;
    private final LectureRepository lectureRepository;

    // 전체 강좌 리스트 뷰로 보내는 컨트롤러
    @GetMapping("/getlecturelist")
    public ResponseEntity lectureList() {
        JSONObject lectureList = lectureService.findAllLecture();
        return new ResponseEntity<>(lectureList.toString(), HttpStatus.OK);
    }

    // 강좌 상세정보 찾아오기
    @GetMapping("/getlecturedetail")
    public ResponseEntity lectureDetail(@RequestParam("name") String name) {
        LectureListDto lecture = lectureRepository.findLecture(name);
        return ResponseEntity.status(HttpStatus.OK).body(lecture);
    }

    // 뷰에서 넘어온 조건으로 강좌 리스트 검색
    @GetMapping("/searchlectlist")
    public ResponseEntity lectSearch(@RequestParam("year") String year, @RequestParam("sem") String sem,
                                     @RequestParam("active") String active, @RequestParam("elem") String elem,
                                     @RequestParam("subject") String subject, @RequestParam("name") String name) {

        // 받아온 변수값 검색을 위해 변형
        boolean isActive = Boolean.parseBoolean(active);
        int elementary = Integer.parseInt(elem);

        // 로그 찍어보기
        logger.info("Received request with year={}, sem={}, isActive={}, elementary={}, subject={}, name={}",
                year, sem, isActive, elementary, subject, name);

        List<LectureListDto> lectureList = lectureRepository.findLectList(year, sem, isActive, subject, name, elementary);
        logger.info("Found DTOs are {}", lectureList);
        return ResponseEntity.status(HttpStatus.OK).body(lectureList);
    }

    // 수정, 삭제, 추가된 데이터 업데이트
    @PutMapping("/savelecturedata")
    public void lectureUpdate(@RequestBody LectureListDto modifiedLecture) {
        logger.info("ReceivedData : {}", modifiedLecture);
    }

}
