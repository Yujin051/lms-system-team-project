package org.example.repository;

import org.example.entity.LectInfo;
import org.example.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 임승범
 */

@Repository
public interface LectInfoRepository extends JpaRepository<LectInfo , Long> {

    // 강사 id로 수업 리스트 가져오기
    List<LectInfo> findByProfessor(Professor professor);
}
