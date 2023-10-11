package org.example.service.admin;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.admin.LectureListDto;
import org.example.entity.LectInfo;
import org.example.entity.Professor;
import org.example.repository.LectureRepository;
import org.json.JSONObject;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    // 전체 강좌 리스트 반환
    public JSONObject findAllLecture() {
        List<LectureListDto> lectureList = lectureRepository.findAllLectList();
        JSONObject object = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject pagination = new JSONObject();
        object.put("result", true);
        object.put("data", data);
        data.put("contents", lectureList);
        data.put("pagination", pagination);
        return object;
    }

    // 새 강좌 저장하는 서비스
    @Transactional
    public void newLecture(Professor professor, String lectName, String lectSubject, String lectYear,
                           String lectSem, Long lectCredit, Long lectNowNum, Long lectMaxNum,
                           LocalDateTime lectStart, LocalDateTime lectEnd, LocalDateTime enrollStart,
                           LocalDateTime enrollEnd, boolean isActive, Long lectAssign, Long lectCheck,
                           Long lectTest, int lectElem) {


        LectInfo lectInfo = LectInfo.builder().professor(professor).lectName(lectName).lectSubject(lectSubject)
                .lectYear(lectYear).lectSem(lectSem).lectCredit(lectCredit).lectNowNum(lectNowNum).lectMaxNum(lectMaxNum)
                .lectStart(lectStart).lectEnd(lectEnd).enrollStart(enrollStart).enrollEnd(enrollEnd).isActive(isActive)
                .lectAssign(lectAssign).lectCheck(lectCheck).lectTest(lectTest).lectElem(lectElem).build();

        lectureRepository.save(lectInfo);
    }

    // 기존 강좌 수정
    @Transactional
    public void updateLecture() {

    }
}
