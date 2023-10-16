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

    @Query("SELECT a FROM Assignments a WHERE a.lectInfo.lectId = :lectId AND a.id = :assignId")
    Assignments findAssignmentByLectIdAndAssignId(@Param("lectId") Long lectId, @Param("assignId") Long assignId);


    @Modifying
    @Query("insert into Assignments (lectInfo, name, detail, start, end, isActive, isSubmit, originFilename, savedFilename) " +
            "SELECT l, :name, :detail, :start, :end, :isActive, :isSubmit, :originFilename, :savedFilename " +
            "FROM LectInfo l WHERE l.lectId = :lectId")
    void addAssignmentToLect(@Param("lectId") Long lectId,
                             @Param("name") String name,
                             @Param("detail") String detail,
                             @Param("start") LocalDate start,
                             @Param("end") LocalDate end,
                             @Param("isActive") boolean isActive,
                             @Param("isSubmit") boolean isSubmit,
                             @Param("originFilename") String originFilename,
                             @Param("savedFilename") String savedFilename);
}
