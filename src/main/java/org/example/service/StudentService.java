package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.CheckGradeDto;
import org.example.dto.EnrolmentDto;
import org.example.entity.LectInfo;
import org.example.repository.GradeInfoRepository;
import org.example.repository.LectInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class StudentService {
    private final LectInfoRepository lectInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;
    public List<EnrolmentDto> lectInfoList() {
        return lectInfoRepository.findEnrolmentDto();
    }
    public List<CheckGradeDto> gradeCheckList() { return gradeInfoRepository.findCheckGradeDto();}

}
