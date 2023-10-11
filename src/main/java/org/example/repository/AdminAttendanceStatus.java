package org.example.repository;


import org.example.dto.AttendanceStatusDto;
import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminAttendanceStatus extends JpaRepository<LectInfo, Long> {

/*    @Query("SELECT NEW org.example.dto.AttendanceStatusDto(lif.lectSubject, lif.isActive, "
            + "lif.lectId, lif.lectSubject, lif.lectStart, lif.lectEnd, lif.lectCredit, s.studId) "
            + "FROM LectInfo lif JOIN lif.student s")

    List<AttendanceStatusDto> findLectInfoDtos(); */


/*    List<LectInfo> findByStudLectApply_Id(Long lectId);*/



}
