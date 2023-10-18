package org.example.repository;

import org.example.dto.AssiGradeLectInfoDto;
import org.example.dto.EnrolmentDto;
import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectInfoRepository extends JpaRepository<LectInfo, Long> {

    @Query("select new org.example.dto.EnrolmentDto(m.userName, l.lectId, l.lectName, l.lectCredit, l.lectNownum, l.lectMaxnum) from LectInfo l join Member m join Professor p where (p.id = l.professor.id and p.member.id = m.id and l.isActive = true" +
            ")")
    List<EnrolmentDto> findEnrolmentDto();

    @Query("select new org.example.dto.AssiGradeLectInfoDto(l.lectId, l.lectName, l.isRecord) from LectInfo l join Member m join Professor p where (p.id = l.professor.id and p.member.id = m.id and l.lectYear = '2023' and l.lectSem = '2학기' and m.id = :memberId)")
    List<AssiGradeLectInfoDto> findAssiGradeLectInfoDto(@Param("memberId") Long memberId);
}
