package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.dto.admin.LectureListDto;
import org.example.entity.Professor;
import org.example.repository.LectureRepository;
import org.example.repository.ProfessorRepository;
import org.example.service.admin.AdminLectureService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureManageController {

    private static final Logger logger = LoggerFactory.getLogger(LectureManageController.class);
    private final AdminLectureService adminLectureService;
    private final LectureRepository lectureRepository;
    private final ProfessorRepository professorRepository;

    // 전체 강좌 리스트 뷰로 보내는 컨트롤러
    @GetMapping("/getlecturelist")
    public ResponseEntity lectureList() {
        JSONObject lectureList = adminLectureService.findAllLecture();
        return new ResponseEntity<>(lectureList.toString(), HttpStatus.OK);
    }

    // 강좌 상세정보 찾아오기
    @GetMapping("/getlecturedetail")
    public ResponseEntity<?> lectureDetail(@RequestParam("id") int id) {
        Long lectId = (long) id;
        LectureListDto lecture = lectureRepository.findLecture(lectId);
        return ResponseEntity.status(HttpStatus.OK).body(lecture);
    }

    // 뷰에서 넘어온 조건으로 강좌 리스트 검색
    @GetMapping("/searchlectlist")
    public ResponseEntity<?> lectSearch(@RequestParam("year") String year, @RequestParam("sem") String sem,
                                        @RequestParam("active") String active, @RequestParam("elem") String elem,
                                        @RequestParam("subject") String subject, @RequestParam("name") String name) {

        // 받아온 변수값 검색을 위해 변형
            boolean isActive = Boolean.parseBoolean(active);
        int elementary = Integer.parseInt(elem);


        // 로그 찍어보기
//        logger.info("Received request with year={}, sem={}, isActive={}, elementary={}, subject={}, name={}",
//                year, sem, isActive, elementary, subject, name);

        List<LectureListDto> lectureList = lectureRepository.findLectList(year, sem, isActive, subject, name, elementary);
//        logger.info("Found DTOs are {}", lectureList);
        return ResponseEntity.status(HttpStatus.OK).body(lectureList);
    }

    // 수정, 추가된 데이터 업데이트
    @PutMapping("/savelecturedata")
    public ResponseEntity<?> lectureUpdate(@RequestBody LectureListDto mLecture) {
        // 이름 가져오기
        String profName = mLecture.getUserName();
        // 넘어온 강좌 id 로깅
//        logger.info("Received LectId : {}", mLecture.getLectId());
        // 해당 교수 아래로 새로운 강좌 생성
        Professor professor = professorRepository.findProfessorByMember_UserName(profName);
        // 뷰에서 강좌 ID 값이 없는 경우 0으로 넘어오므로 0일 때와 아닐 때로 처리
        if (mLecture.getLectId() == 0) {
            if(professor != null) {
                adminLectureService.newLecture(profName, mLecture.getLectName(), mLecture.getLectSubject(), mLecture.getLectYear(),
                        mLecture.getLectSem(), mLecture.getLectCredit(), mLecture.getLectNowNum(), mLecture.getLectMaxNum(),
                        mLecture.getLectStart(), mLecture.getLectEnd(), mLecture.getEnrollStart(), mLecture.getEnrollEnd(),
                        mLecture.isActive(), mLecture.getLectAssign(), mLecture.getLectCheck(), mLecture.getLectTest(),
                        mLecture.getLectElem());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 강사가 존재하지 않습니다.");
            }
        } else {
            adminLectureService.updateLecture(mLecture.getLectId(), profName, mLecture.getLectName(), mLecture.getLectSubject(),
                    mLecture.getLectYear(), mLecture.getLectSem(), mLecture.getLectCredit(), mLecture.getLectNowNum(),
                    mLecture.getLectMaxNum(), mLecture.getLectStart(), mLecture.getLectEnd(), mLecture.getEnrollStart(),
                    mLecture.getEnrollEnd(), mLecture.isActive(), mLecture.getLectAssign(), mLecture.getLectCheck(), mLecture.getLectTest(),
                    mLecture.getLectElem());
        }
        // 새로 저장된 강좌를 포함한 전체 강좌 리스트 반환

        List<LectureListDto> lectureList = lectureRepository.findAllLectList();
        return new ResponseEntity<>(lectureList, HttpStatus.OK);
    }

    @DeleteMapping("/deletelecturedata")
    public void lectureDelete(@RequestBody List<LectureListDto> deletedRows) {
        // 삭제된 행 정보를 받아와서 해당하는 강좌 ID로 DB에서 삭제
        for(LectureListDto lectureListDto : deletedRows) {
            Long lectId = lectureListDto.getLectId();
            // 받아온 강좌 ID가 없다면 삭제 실행하지 않음
            if(lectId != null) {
                lectureRepository.deleteById(lectId);
            }
        }
    }
}
