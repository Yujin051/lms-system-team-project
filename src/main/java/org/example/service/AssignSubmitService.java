package org.example.service;

import org.example.entity.AssignSubmit;
import org.example.repository.AssignSubmitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignSubmitService {

    @Autowired
    private AssignSubmitRepository assignSubmitRepository;

//    public List<AssignSubmit> getSubmissionsByLectId(Long lectId) {
//        return assignSubmitRepository.findByAssignmentsLectInfoLectId(lectId);
//    }

    public void createAssignmentSubmit(AssignSubmit submission) {
        assignSubmitRepository.save(submission);
    }


}
