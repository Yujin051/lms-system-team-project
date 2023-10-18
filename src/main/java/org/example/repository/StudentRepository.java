package org.example.repository;

import org.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query("SELECT s FROM Student s WHERE s.member.lectInfo.lectId = :lectId AND s.member.lectInfo.professor.id = :assiId")
//    List<Student> findByLectIdAndAssiId(@Param("lectId") String lectId, @Param("assiId") Long assiId);
}
