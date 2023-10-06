package org.example.repository;

import org.example.dto.CheckGradeDto;
import org.example.entity.GradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeInfoRepository extends JpaRepository<GradeInfo, Long> {

    @Query("select new org.example.dto.CheckGradeDto(l.lectId, l.lectName, l.lectCredit, m.userName, g.grade) from GradeInfo g join LectInfo l join Member m join Professor p join Student s where (p.id = l.professor.id and p.member.id = m.id and l.lectId = g.lectInfo.lectId and s.studId = g.student.studId)")
    List<CheckGradeDto> findCheckGradeDto();

}
