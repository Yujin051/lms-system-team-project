package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.AssiGradeLectInfoDto;
import org.example.dto.EnteringGradeDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.repository.GradeInfoRepository;
import org.example.repository.LectInfoRepository;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class ProfessorService{
    private final ProfessorRepository professorRepository;
    private final LectInfoRepository lectInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    // 성적입력에서 강좌정보들고오는거에 대한 서비스
    public List<AssiGradeLectInfoDto> AssiGradeLectInfoCheckList(Long memberId) {
        List<AssiGradeLectInfoDto> assiGradeLectInfoDtos = lectInfoRepository.findAssiGradeLectInfoDto(memberId);
        return assiGradeLectInfoDtos;
    }

    // 성적입력에 관한 정보 들고오는 서비스
    public List<EnteringGradeDto> EnteringGradeCheckList(Long lectId) {
        List<EnteringGradeDto> enteringGradeDtos = gradeInfoRepository.findEnteringGradeDto(lectId);
        return enteringGradeDtos;
    }


}
