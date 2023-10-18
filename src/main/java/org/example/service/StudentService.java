package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student findByMember(Member member){

        Student student = studentRepository.findByMember(member);

        return student;
    }


}
