package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.entity.Assignments;
import org.example.repository.AssignmentsRepository;
import org.example.repository.LectInfoRepository;
import org.example.repository.StudLectApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class AssignmentsService {

    //과제파일 저장경로 설정
    @Value("C:\\Users\\admin\\IdeaProjects\\lms-system-team-project2\\src\\main\\resources\\static\\file\\assignment\\")
    String filePath;

    @Autowired
    private final AssignmentsRepository assignmentsRepository;

    @Autowired
    private final LectInfoRepository lectInfoRepository;



    // 파일 업로드 관련 메소드
    public String getFullPath(String filename) {
        return filePath + filename;
    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    public List<Assignments> assignmentViewByLectId(long id) {
        return assignmentsRepository.findByLectInfoLectId(id);
    }

    public void saveAssignment(Assignments assignments, MultipartFile file) throws Exception {
        // 원본 파일명
        String originalFilename = file.getOriginalFilename();

        // 서버에 저장된 파일명
        // 파일명이 중복될 수 있으므로 UUID로 설정
        String savedFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        // 파일 저장
        file.transferTo(new File(getFullPath(savedFilename)));

        // 오리지널 이미지 이름 저장
        assignments.setOriginFilename(originalFilename);

        // DB 이미지 이름 저장
        assignments.setSavedFilename(savedFilename);

        assignmentsRepository.save(assignments);
    }
}
