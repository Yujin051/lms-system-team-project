package org.example.repository;

import org.example.entity.StudLectApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudLectApplyRepository extends JpaRepository <StudLectApply, Long> {
}
