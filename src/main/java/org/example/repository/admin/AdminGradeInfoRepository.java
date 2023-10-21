package org.example.repository.admin;

import org.example.entity.GradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminGradeInfoRepository extends JpaRepository<GradeInfo, Long> {
}
