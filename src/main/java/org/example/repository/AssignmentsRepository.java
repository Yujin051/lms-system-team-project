package org.example.repository;

import org.example.entity.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {
    List<Assignments> findByLectInfoLectId(Long lectId);

    @Query("SELECT a FROM Assignments a WHERE a.lectInfo.lectId = :lectId AND a.assiId = :assignId")
    Assignments findAssignmentByLectIdAndAssignId(@Param("lectId") Long lectId, @Param("assignId") Long assignId);


    Assignments findByAssiId(Long assiId);


}
