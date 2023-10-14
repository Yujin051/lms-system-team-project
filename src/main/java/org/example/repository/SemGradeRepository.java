package org.example.repository;

import jakarta.persistence.Id;
import org.example.dto.CheckGradeDto;
import org.example.dto.CheckSemGradeDto;
import org.example.entity.SemGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SemGradeRepository extends JpaRepository <SemGrade, Long> {

    @Query("select new org.example.dto.CheckSemGradeDto(sg.semYear, sg.semSem) from SemGrade sg  join Member m join Student s where sg.student.studId = s.studId and s.member.id = m.id and m.id = :memberId")
    List<CheckSemGradeDto> findCheckSemGradeDto(@Param("memberId") Long memberId);
}
