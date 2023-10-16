package org.example.service;


import lombok.extern.log4j.Log4j2;
import org.example.entity.Assignments;
import org.example.entity.LectInfo;
import org.example.entity.Member;
import org.example.repository.LectInfoRepository;
import org.example.repository.StudLectApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class LectureService {



    @Autowired
    private StudLectApplyRepository studLectApplyRepository;

    @Autowired
    private LectInfoRepository lectInfoRepository;




    public List<LectInfo> findCoursesByMemberAndSemester(Long memberId, String year, String semester) {
        log.info(studLectApplyRepository.findCoursesByMemberAndSemester(memberId, year, semester));
        return studLectApplyRepository.findCoursesByMemberAndSemester(memberId, year, semester);
    }

    public LectInfo lectureView(Long id) {
        return lectInfoRepository.findById(id).get();
    }

    public List<LectInfo> findCoursesByProfessorAndSemester(Long professorId, String year, String semester) {
        // LectInfoRepository를 사용하여 강사가 개설한 강좌를 조회
        List<LectInfo> lectInfoList = lectInfoRepository.findLectInfoByProfessorAndSemester(professorId, year, semester);
        return lectInfoList;
    }

}
