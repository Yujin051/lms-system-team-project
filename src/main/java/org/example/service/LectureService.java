package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.entity.LectInfo;
import org.example.repository.LectInfoRepository;
import org.example.repository.StudLectApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
