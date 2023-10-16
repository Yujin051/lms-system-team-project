package org.example.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.StudLectProgDto;
import org.example.entity.StudLectProg;
import org.example.repository.admin.StudLectProgRepository;
import org.springframework.stereotype.Service;

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
//    // 마지막 재생 위치의 시간만 데이터베이스에 저장
//    public void getSaveLastPlayTime(Long magId, int lastPlayTime) {
//        StudLectProg studLectProg = studLectProgRepository.findById(magId)
//                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));
//        if (studLectProg != null) {
//            studLectProg.setFnlPosi(lastPlayTime);
//            studLectProgRepository.save(studLectProg);
//        }
//    }

    public List<StudLectProgDto> getFindStudLectProg() {
        return studLectProgRepository.findStudLectProg();
    }

    // 최종재생위치(fnlPosi)와 최대재생위치(maxPosi)를 5초마다 데이터베이스에 저장
    public void savePlayTime(Long magId, double fnlPosi, double maxPosi) {
        StudLectProg studLectProg = studLectProgRepository.findById(magId)
                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));

        if (studLectProg != null) {
            if (fnlPosi > maxPosi) {
                fnlPosi = maxPosi;
            }

            studLectProg.setMaxPosi(maxPosi); // 최대 재생 위치 업데이트
            studLectProg.setFnlPosi(fnlPosi); // 최종 재생 위치 업데이트
            studLectProgRepository.save(studLectProg); // 업데이트된 객체를 저장
        }
    }

    // 최종재생위치만 데이터베이스에 저장
    public void saveFnlPosi(Long magId, double fnlPosi) {
        StudLectProg studLectProg = studLectProgRepository.findById(magId)
                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));

        studLectProg.setFnlPosi(fnlPosi);
        studLectProgRepository.save(studLectProg);
    }

    // 최종재생위치만 데이터베이스에 저장
    public void saveMaxPosi(Long magId, double maxPosi) {
        StudLectProg studLectProg = studLectProgRepository.findById(magId)
                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));

        studLectProg.setMaxPosi(maxPosi);
        studLectProgRepository.save(studLectProg);
    }

}
