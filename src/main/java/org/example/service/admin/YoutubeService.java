package org.example.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.StudLectProgDto;
import org.example.entity.StudLectProg;
import org.example.repository.admin.StudLectProgRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class YoutubeService {

    private final StudLectProgRepository studLectProgRepository;

    // 수강생차시진도 기본키 조회
    public StudLectProgDto getFindMagId() {
        return studLectProgRepository.findMagId();
    }

    // 수강생차시진도 모두 조회
    public List<StudLectProgDto> getFindStudLectProg() {
        return studLectProgRepository.findStudLectProg();
    }

    // 최종재생위치(fnlPosi)와 최대재생위치(maxPosi)를 데이터베이스에 저장
    public void savePlayTime(Long magId, double fnlPosi, double maxPosi) {
        StudLectProg studLectProg = studLectProgRepository.findById(magId)
                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));
        log.info("serfnlPosi : " + fnlPosi);
        log.info("sermaxPosi : " + maxPosi);
        if (maxPosi >= studLectProg.getMaxPosi()) {
            studLectProg.setMaxPosi(maxPosi);
        }
        studLectProg.setFnlPosi(fnlPosi); // 최종 재생 위치 업데이트
        studLectProgRepository.save(studLectProg);
    }

    // 진행률 데이터베이스에 저장
    public double saveProgress(Long magId, double progress) {
        StudLectProg studLectProg = studLectProgRepository.findById(magId)
                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));

        // 만약, 진행률 70프로가 넘으면 출석 상태를 true로 바꾸고 현재 시간 찍히게
        if (progress >= 10 && !studLectProg.getIsChecked()) {
            studLectProg.setIsChecked(true); //출석상태 true로 저장
            LocalDateTime currentDateTime = LocalDateTime.now(); // 현재 날짜 및 시간 얻기

            // 원하는 날짜 및 시간 형식을 포맷
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // String에서 LocalDateTime으로 변환
            String formattedDateTime = currentDateTime.format(formatter);
            LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, formatter);

            studLectProg.setCheckDate(localDateTime); //출석날짜 저장
        }
        log.info("serProgress : " + progress);
        studLectProg.setProgress(progress); //출석률 저장
        studLectProgRepository.save(studLectProg); // 데이터베이스에 저장
        return progress;
    }


}
