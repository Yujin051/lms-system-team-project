package org.example.repository;

import org.example.entity.StudLectApply;
import org.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 임승범
 */
@Repository
public interface StudLectApplyRepository extends JpaRepository<StudLectApply , Long> {

    // 학생정보로 신청한 강좌 리스트 가져오기
    List<StudLectApply> findByStudent(Student student);

}
