package org.example.repository;

import org.example.entity.SemGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemGradeRepository extends JpaRepository<SemGrade, Long> {
}
