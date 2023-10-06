package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProfessorDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ProfessorService{
    private final ProfessorRepository professorRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }


    public void professorUpdate(Professor professor) {
        professorRepository.save(professor);
    }

    public List<ProfessorDto> ProfessorView(String Id) {
    Long memberId = memberRepository.findByUserId(Id).getId();
    return professorRepository.findByProfessorId(memberId);
}

    public Professor ProfessorView(Long Id) {
        return professorRepository.findById(Id).orElse(null);
    }

}
