package org.example.repository;

import org.example.entity.LectInfo;
import org.example.entity.StudLectApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudLectApplyRepository extends JpaRepository<StudLectApply, Long> {



    // 가져온 사용자 이름을 JPQL 쿼리에 적용합니다.
//    @Query("SELECT c FROM StudLectApply c " +
//            "JOIN c.student s " +
//            "JOIN c.sem_sem sem " +
//            "WHERE s.userName = :username " +
//            "AND sem.lectYear = '2021' " +
//            "AND sem.lectSem = '1학기'")
//    List<StudLectApply> getCoursesForUser(@Param("username") String username);

    @Query("SELECT sa.lectInfo FROM StudLectApply sa WHERE sa.student.studId = :studentId")
    List<LectInfo> findLectByStudentId(@Param("studentId") Long studentId);

//    @Query("SELECT sla.lectInfo FROM StudLectApply sla " +
//            "INNER JOIN sla.student s " +
//            "INNER JOIN s.member m " +
//            "INNER JOIN sla.lectInfo li " +
//            "INNER JOIN li.professor p " +
//            "WHERE m.id = :memberId " +
//            "AND li.lectYear = :year " +
//            "AND li.lectSem = :semester")
//    List<LectInfo> findCoursesByMemberAndSemester(@Param("memberId") Long memberId, @Param("year") String year, @Param("semester") String semester);

    @Query("SELECT li FROM StudLectApply sla " +
            "INNER JOIN sla.student s " +
            "INNER JOIN s.member m " +
            "INNER JOIN sla.lectInfo li " +
            "WHERE m.id = :memberId " +
            "AND li.lectYear = :year " +
            "AND li.lectSem = :semester")
    List<LectInfo> findCoursesByMemberAndSemester(@Param("memberId") Long memberId, @Param("year") String year, @Param("semester") String semester);
}
