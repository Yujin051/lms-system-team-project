package org.example.repository;

import org.example.entity.Member;
import org.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 임승범
 */

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

    // 사용자정보로 학생정보 가져오기
    Student findByMember(Member member);

}
