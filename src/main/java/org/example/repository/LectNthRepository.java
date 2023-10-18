package org.example.repository;


import org.example.entity.LectNth;
import org.example.entity.LmsConts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectNthRepository extends JpaRepository<LectNth, Long> {

    // 해당 컨텐츠 정보가 강좌에 등록되어 있는지 확인
    Optional<LectNth> findByLmsConts(LmsConts lmsConts);
}
