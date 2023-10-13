package org.example.controller.admin;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.admin.ApplyStudentDto;
import org.example.dto.admin.DeleteApplyStudentDto;
import org.example.dto.admin.LectureListDto;
import org.example.entity.LectInfo;
import org.example.entity.Student;
import org.example.repository.LectApplyRepository;
import org.example.repository.LectureRepository;
import org.example.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

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
//        System.out.println(id);
        List<ApplyStudentDto> applyStudentDtos = lectApplyRepository.findAllByLectId(id);
//        System.out.println(applyStudentDtos.toString());
        return ResponseEntity.status(HttpStatus.OK).body(applyStudentDtos);
    }

    // 수강신청 된 학생들 삭제(수강취소) 메소드
    @DeleteMapping("/deletestudent")
    @Transactional
    public void lectureDelete(@RequestBody DeleteApplyStudentDto deletedRows) {
        Long lectId = deletedRows.getLectId();
        List<ApplyStudentDto> deleteRows = deletedRows.getDeleteRows();
        // 삭제된 행 정보를 받아와서 해당하는 강좌 ID로 DB에서 삭제
        for(ApplyStudentDto applyStudentDto : deleteRows) {
            Long StudId = applyStudentDto.getStudId();
            // 받아온 학생 ID가 없다면 삭제 실행하지 않음(서비스로 분리하기?)
            if(StudId != null) {
                Student student = studentRepository.findById(StudId).
                        orElseThrow(EntityNotFoundException::new);
                LectInfo lectInfo = lectureRepository.findById(lectId)
                        .orElseThrow(EntityNotFoundException::new);
                lectApplyRepository.deleteByStudentAndLectInfo(student, lectInfo);
            }
        }
    }

}
