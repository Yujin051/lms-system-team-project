package org.example.repository;

import org.example.dto.EnrolmentDto;
import org.example.entity.LectInfo;
import org.example.entity.StudLectApply;
import org.example.entity.Student;
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

    // 학생정보로 신청한 강좌 리스트 가져오기
    List<StudLectApply> findByStudent(Student student);

    // 수업id로 강좌 정보 가져오기

    // 수업id로 수강생 리스트 가져오기
    @Query("SELECT sla.student FROM StudLectApply sla WHERE sla.lectInfo.lectId = :id")
    List<Student> findClassMate(@Param("id") Long id);

    @Query("select new org.example.dto.EnrolmentDto(sl.applyId, p.member.userName, l.lectId, l.lectName, l.lectCredit, l.lectNownum, l.lectMaxnum) from StudLectApply sl join LectInfo l join Member m join Professor p join Student s where (p.id = l.professor.id and l.isActive = true and m.id = :memberId and m.id = s.member.id and sl.student.studId = s.studId and sl.lectInfo.lectId = l.lectId" +
            ")")
    List<EnrolmentDto> findApplyEnrolmentDto(@Param("memberId") Long memberId);


    boolean existsByStudentAndLectInfo(Student student, LectInfo lectInfo);


}
