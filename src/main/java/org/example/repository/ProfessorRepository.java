package org.example.repository;

import org.example.dto.ProfessorDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("select NEW org.example.dto.ProfessorDto(p.id, p.member.id, p.profAgency, p.profWork, p.profBank, p.profAccount, p.isActive) from Professor p join Member m where p.member.id = m.id")
    List<ProfessorDto> findByProfessorId(Long memberId);

    @Query("select NEW org.example.dto.ProfessorDto(p.id, p.member.id, p.profAgency, p.profWork, p.profBank, p.profAccount, p.isActive) from Professor p join Member m where p.member.id = m.id")
    Professor findByProfId(Member memberId);





}

