package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProfessorDto;
import org.example.dto.AssiGradeLectInfoDto;
import org.example.dto.EnteringGradeDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.entity.Student;
import org.example.repository.GradeInfoRepository;
import org.example.repository.LectInfoRepository;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class ProfessorService{
    private final ProfessorRepository professorRepository;
    private final LectInfoRepository lectInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    /**
     *
     * @param member
     * @return
     */
    public Professor findByMember(Member member){

        Professor professor = professorRepository.findByMember(member);

        return professor;
    }


    public void professorUpdate(Professor professor) {
        professorRepository.save(professor);
    }

    public Professor ProfessorView(String Id) {
    Long memberId = memberRepository.findByUserId(Id).getId();
    return professorRepository.findByProfessorId(memberId);
}

    public Professor ProfessorView(Long Id) {
        return professorRepository.findByProfessorId(Id);
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

    public ProfessorDto mapToProfessorDto(Professor professor) {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setProfId(professor.getProfId());
        professorDto.setMemberId(professor.getMember().getId());
        professorDto.setProfAgency(professor.getProfAgency());
        professorDto.setProfWork(professor.getProfWork());
        professorDto.setProfBank(professor.getProfBank());
        professorDto.setProfAccount(professor.getProfAccount());
        professorDto.setActive(professor.isActive());
        return professorDto;
    }

    public List<ProfessorDto> mapToProfessorDtoList(List<Professor> professors) {
        return professors.stream()
                .map(this::mapToProfessorDto)
                .collect(Collectors.toList());
    }

    public void updateProfessor(Professor professor, MultipartFile file) throws Exception {
        file = null;
    }
}
