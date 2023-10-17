package org.example.repository;


import org.example.dto.LectInfoDto;
import org.example.dto.LectNthDto;
import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectInfoRepository extends JpaRepository<LectInfo, Long> {

    // 온라인 강의 조회
    @Query("SELECT NEW org.example.dto.LectNthDto " +
            "(lif.lectId, lif.lectName, lif.lectSubject, lif.lectStart, lif.lectEnd, lif.isActive) " +
            "FROM LectInfo lif")
    List<LectNthDto> findLectInfo2();
}
