package org.example.repository;

import jakarta.persistence.Column;
import org.example.entity.LectInfo;
import org.example.entity.LectNth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAttendanceStatus extends JpaRepository<LectInfo, Long> {

/*    @Column("select new ")*/


}
