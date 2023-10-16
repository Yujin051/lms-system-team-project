package org.example.repository;

import org.example.entity.LectInfo;
import org.example.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 임승범
 */

@Repository
public interface LectInfoRepository extends JpaRepository<LectInfo , Long> {

    // 수업정보 id로 강사 가져오기
//    LectInfo findByLectId(Long id);
}
