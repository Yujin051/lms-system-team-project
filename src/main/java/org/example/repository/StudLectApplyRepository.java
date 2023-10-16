package org.example.repository;

import org.example.entity.StudLectApply;
import org.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 임승범
 */
@Repository
public interface StudLectApplyRepository extends JpaRepository<StudLectApply , Long> {

    // 학생정보로 신청한 강좌 리스트 가져오기
    List<StudLectApply> findByStudent(Student student);

    // 수업id로 강좌 정보 가져오기

    // 수업id로 수강생 리스트 가져오기
    @Query("SELECT sla.student FROM StudLectApply sla WHERE sla.lectInfo.lectId = :id")
    List<Student> findClassMate(@Param("id") Long id);

}
