package org.example.repository;


import org.example.dto.AttendanceStatusDto;
import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminAttendanceStatus extends JpaRepository<LectInfo, Long> {


}
