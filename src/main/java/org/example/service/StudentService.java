package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.CheckGradeDto;
import org.example.dto.CheckSemGradeDto;
import org.example.dto.EnrolmentDto;
import org.example.entity.GradeInfo;
import org.example.entity.LectInfo;
import org.example.repository.*;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class StudentService {
    private final LectInfoRepository lectInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;
    private final SemGradeRepository semGradeRepository;
    private final StudLectApplyRepository studLectApplyRepository;
    public List<EnrolmentDto> lectInfoList() {
        return lectInfoRepository.findEnrolmentDto();
    }
    public List<CheckGradeDto> gradeCheckList(Long id) {
        // 사용자 정보id(Long)로 db 조회 , 성적dto 리스트 반환
        List<CheckGradeDto> gradeDtos = gradeInfoRepository.findCheckGradeDto(id);
        return gradeDtos;
    }

    public List<CheckGradeDto> detailGradeCheckList(Long id, String semYear, String semSem) {
        List<CheckGradeDto> detailGradeDtos = gradeInfoRepository.findDetailCheckGradeDto(id, semYear, semSem);
        return detailGradeDtos;
    }

    public List<CheckSemGradeDto> semGradeCheckList(Long id) {
        List<CheckSemGradeDto> semGradeDtos = semGradeRepository.findCheckSemGradeDto(id);
        return semGradeDtos;
    }

    public List<EnrolmentDto> studLectApplyCheckList(Long memberId) {
        List<EnrolmentDto> studLectApplyDtos = studLectApplyRepository.findApplyEnrolmentDto(memberId);
        return studLectApplyDtos;
    }

}
