package org.example.service.admin;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.admin.LectureListDto;
import org.example.entity.LectInfo;
import org.example.entity.Professor;
import org.example.repository.LectureRepository;
import org.example.repository.ProfessorRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminLectureService {
    private final LectureRepository lectureRepository;
    private final ProfessorRepository professorRepository;

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
    public void newLecture(String profName, String lectName, String lectSubject, String lectYear,
                           String lectSem, Long lectCredit, Long lectNowNum, Long lectMaxNum,
                           LocalDateTime lectStart, LocalDateTime lectEnd, LocalDateTime enrollStart,
                           LocalDateTime enrollEnd, boolean isActive, Long lectAssign, Long lectCheck,
                           Long lectTest, int lectElem) {
        // 이름으로 교수 객체 찾아오기
        Professor professor = professorRepository.findProfessorByMember_UserName(profName);

        // 찾아온 교수 객체와 넘어온 필드값으로 새로운 강좌 생성
        LectInfo lectInfo = LectInfo.builder().professor(professor).lectName(lectName).lectSubject(lectSubject)
                .lectYear(lectYear).lectSem(lectSem).lectCredit(lectCredit).lectNownum(lectNowNum).lectMaxnum(lectMaxNum)
                .lectStart(lectStart).lectEnd(lectEnd).enrollStart(enrollStart).enrollEnd(enrollEnd).isActive(isActive)
                .lectAssign(lectAssign).lectCheck(lectCheck).lectTest(lectTest).lectElem(lectElem).build();

        lectureRepository.save(lectInfo);
    }

    // 기존 강좌 수정
    @Transactional
    public void updateLecture(Long lectId, String profName, String lectName, String lectSubject, String lectYear,
                              String lectSem, Long lectCredit, Long lectNownum, Long lectMaxnum,
                              LocalDateTime lectStart, LocalDateTime lectEnd, LocalDateTime enrollStart,
                              LocalDateTime enrollEnd, boolean isActive, Long lectAssign, Long lectCheck,
                              Long lectTest, int lectElem) {
        // JPA 더티체크 확인해보기 -> 넘어온 모든 필드값 수정하는 방식
        // 받아온 교수 이름으로 교수 객체 찾기
        Professor professor = professorRepository.findProfessorByMember_UserName(profName);

        // 받아온 강좌 ID로 수정할 강좌 찾기, 없다면 예외 발생
        LectInfo lecture = lectureRepository.findById(lectId).orElseThrow(EntityNotFoundException::new);

        // 각 필드에 새로운 값 할당, 더티체킹 통해 자동으로 update 쿼리 실행
        lecture.setProfessor(professor);
        lecture.setLectName(lectName);
        lecture.setLectSubject(lectSubject);
        lecture.setLectYear(lectYear);
        lecture.setLectSem(lectSem);
        lecture.setLectCredit(lectCredit);
        lecture.setLectNownum(lectNownum);
        lecture.setLectMaxnum(lectMaxnum);
        lecture.setLectStart(lectStart);
        lecture.setLectEnd(lectEnd);
        lecture.setEnrollStart(enrollStart);
        lecture.setEnrollEnd(enrollEnd);
        lecture.setActive(isActive);
        lecture.setLectAssign(lectAssign);
        lecture.setLectCheck(lectCheck);
        lecture.setLectTest(lectTest);
        lecture.setLectElem(lectElem);

    }
}
