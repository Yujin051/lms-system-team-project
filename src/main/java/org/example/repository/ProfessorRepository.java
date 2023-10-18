package org.example.repository;

import org.example.dto.ProfessorDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.member.id = :memberId")
    Professor findByProfessorId(@Param("memberId") Long memberId);


    Professor findByMember(Member member);

}

