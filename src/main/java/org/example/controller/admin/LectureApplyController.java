package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.dto.admin.ApplyStudentDto;
import org.example.dto.admin.LectureListDto;
import org.example.repository.LectApplyRepository;
import org.example.repository.LectureRepository;
import org.example.service.admin.LectureService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apply/")
@RequiredArgsConstructor
public class LectureApplyController {

    private final LectureService lectureService;
    private final LectApplyRepository lectApplyRepository;
    private final LectureRepository lectureRepository;

    // 수강신청 대상 강좌 리스트 불러오기
    @GetMapping("/lecturelist")
    public ResponseEntity<?> lectList() {
        JSONObject lectureList = lectureService.findAllLecture();
        return ResponseEntity.status(HttpStatus.OK).body(lectureList.toString());
    }

    // 검색 조건으로 강좌 리스트 불러오기
    @PostMapping("/lecturelist")
    public ResponseEntity<?> searchLectList(@RequestParam("year") String year, @RequestParam("sem") String sem,
                                            @RequestParam("name") String name) {

        List<LectureListDto> lectureList = lectureRepository.findApplyLectList(year, sem, name);
        return ResponseEntity.status(HttpStatus.OK).body(lectureList);
    }


    // 대상 강좌 수강신청 한 학생들 불러오기
    @GetMapping("/studentlist")
    public ResponseEntity<?> studentList(@RequestParam(name = "lectId") Long id) {
        System.out.println(id);
        List<ApplyStudentDto> applyStudentDtos = lectApplyRepository.findAllByLectId(id);
        System.out.println(applyStudentDtos.toString());
        return ResponseEntity.status(HttpStatus.OK).body(applyStudentDtos);
    }


}
