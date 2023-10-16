package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Member;
import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * @author 임승범
 */

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student findByMember(Member member){

        Student student = studentRepository.findByMember(member);

        return student;
    }

}
