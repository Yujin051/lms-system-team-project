package org.example.repository;

import org.example.entity.GradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeInfoRepository extends JpaRepository<GradeInfo, Long> {
}
