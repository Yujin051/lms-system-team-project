package org.example.service;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    public List<Student> findStudentInfoByLectIdAndAssiId(String lectId, Long assiId) {
//        // JPA 쿼리 메소드를 사용하여 학생 정보를 검색합니다.
//        return studentRepository.findByLectIdAndAssiId(lectId, assiId);
//    }
}
