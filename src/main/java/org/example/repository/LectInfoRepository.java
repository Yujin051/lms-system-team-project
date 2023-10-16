package org.example.repository;

import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectInfoRepository extends JpaRepository<LectInfo, Long> {
    @Query("SELECT li FROM LectInfo li " +
            "WHERE li.professor.member.id = :professorId " +
            "AND li.lectYear = :year " +
            "AND li.lectSem = :semester")
    List<LectInfo> findLectInfoByProfessorAndSemester(@Param("professorId") Long professorId, @Param("year") String year, @Param("semester") String semester);
}
