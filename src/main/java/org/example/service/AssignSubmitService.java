package org.example.service;

import org.example.entity.AssignSubmit;
import org.example.entity.Assignments;
import org.example.repository.AssignSubmitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class AssignSubmitService {

    @Autowired
    private AssignSubmitRepository assignSubmitRepository;

//    public List<AssignSubmit> getSubmissionsByLectId(Long lectId) {
//        return assignSubmitRepository.findByAssignmentsLectInfoLectId(lectId);
//    }
    @Value("C:\\Users\\admin\\IdeaProjects\\lms-system-team-project2\\src\\main\\resources\\static\\file\\assignment\\")
    String filePath;

    public String getFullPath(String filename) {
        return filePath + filename;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


    public void createAssignmentSubmit(AssignSubmit submission) {
        assignSubmitRepository.save(submission);
    }

    public void saveAssignSubmit(AssignSubmit assignSubmit) {
        assignSubmitRepository.save(assignSubmit);
    }

    public void saveAssignmentSubmit(AssignSubmit assignSubmit, MultipartFile file) throws Exception {
        if (file != null) {
            // 원본 파일명
            String originalFilename = file.getOriginalFilename();

            // 서버에 저장된 파일명
            // 파일명이 중복될 수 있으므로 UUID로 설정
            String savedFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

            // 파일 저장
            file.transferTo(new File(getFullPath(savedFilename)));

            // 파일 정보를 엔티티에 설정
            assignSubmit.setOriginalName(originalFilename);
            assignSubmit.setSavedName(savedFilename);
        }

        // Assignments 엔티티 저장
        assignSubmitRepository.save(assignSubmit);
    }

}
