package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.entity.Assignments;
import org.example.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssignmentsService {
    @Autowired
    private final AssignmentsRepository assignmentsRepository;

    public List<Assignments> assignmentViewByLectId(long id) {
        return assignmentsRepository.findByLectInfoLectId(id);
    }
}
