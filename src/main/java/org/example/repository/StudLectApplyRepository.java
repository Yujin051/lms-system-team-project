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

    @Query("select new org.example.dto.EnrolmentDto(sl.applyId, p.member.userName, l.lectId, l.lectName, l.lectCredit, l.lectNownum, l.lectMaxnum) from StudLectApply sl join LectInfo l join Member m join Professor p join Student s where (p.id = l.professor.id and l.isActive = true and m.id = :memberId and m.id = s.member.id and sl.student.studId = s.studId and sl.lectInfo.lectId = l.lectId" +
            ")")
    List<EnrolmentDto> findApplyEnrolmentDto(@Param("memberId") Long memberId);


    boolean existsByStudentAndLectInfo(Student student, LectInfo lectInfo);
}
