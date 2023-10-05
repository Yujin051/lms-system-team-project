package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Transactional
@Service
public class ProfessorService{
    private final ProfessorRepository professorRepository;

    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

}
