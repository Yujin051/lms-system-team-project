package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.StudLectApply;
import org.example.entity.Student;
import org.example.repository.StudLectApplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudLectApplyService {

    private final StudLectApplyRepository studLectApplyRepository;

    // 학생정보로 수강신청한 레코드 리스트 가져오기
    public List<StudLectApply> getStudLectApply(Student student){

        List<StudLectApply> article = studLectApplyRepository.findByStudent(student);

        return article;
    }

    // 강의번호 수강하는 학생들 리스트 반환



}
