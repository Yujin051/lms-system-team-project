package org.example.repository;

import org.example.dto.CheckGradeDto;
import org.example.entity.GradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeInfoRepository extends JpaRepository<GradeInfo, Long> {

    @Query("select new org.example.dto.CheckGradeDto(l.lectId, l.lectName, l.lectCredit, p.member.userName, g.grade) from GradeInfo g join LectInfo l join Member m join Professor p join Student s where (p.id = l.professor.id  and l.lectId = g.lectInfo.lectId and s.studId = g.student.studId and m.id = s.member.id and l.lectYear = '2023' and l.lectSem = '2학기' and m.id = :memberId)")
    List<CheckGradeDto> findCheckGradeDto(@Param("memberId") Long memberId);

    @Query("select new org.example.dto.CheckGradeDto(l.lectId, l.lectName, l.lectCredit, p.member.userName, g.grade) from GradeInfo g join LectInfo l join Member m join Professor p join Student s where (p.id = l.professor.id  and l.lectId = g.lectInfo.lectId and s.studId = g.student.studId and m.id = s.member.id and l.lectYear = :semYear and l.lectSem = :semSem and m.id = :memberId)")
    List<CheckGradeDto> findDetailCheckGradeDto(@Param("memberId") Long memberId, @Param("semYear") String semYear, @Param("semSem") String semSem);


}