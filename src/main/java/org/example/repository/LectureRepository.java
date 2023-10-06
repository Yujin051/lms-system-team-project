package org.example.repository;

import org.example.entity.LectInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<LectInfo, Long> {

}
