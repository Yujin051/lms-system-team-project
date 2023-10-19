package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LectNthDto;
import org.example.dto.LmsContsDto;
import org.example.entity.LectInfo;
import org.example.entity.LectNth;
import org.example.entity.LmsConts;
import org.example.repository.admin.AdminLectInfoRepository;
import org.example.repository.LectNthRepository;
import org.example.repository.LmsContsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectNthService {

    private final LectNthRepository lectNthRepository;
    private final LmsContsRepository lmsContsRepository;
    private final AdminLectInfoRepository adminLectInfoRepository;

    public List<LectNthDto> lectNthList(String searchType, Boolean nthKeyword) {
        return lectNthRepository.findLectNthDtos(searchType, nthKeyword);
    }

    /* search */
    @Transactional
    public List<LectNth> search(String keyword) {
        return lectNthRepository.findByNthIdContaining(keyword);
    }

    public List<LectNthDto> getLectNthData(String searchType, Boolean nthKeyword) {
        return lectNthRepository.findLectNthDtos(searchType, nthKeyword);
    }

    // 온라인 강좌명 조회기능
    public List<LectNthDto> getFindLectNthSearch(String lectName, Boolean isActive) {
        return lectNthRepository.findLectNthSearch(lectName, isActive);
    }

    public List<LectNthDto> getFindLectNthBox(Boolean isActive) {
        return lectNthRepository.findLectNthBox(isActive);
    }

    public List<LectNthDto> getFindLectName(String lectName) {
        return lectNthRepository.findLectName(lectName);
    }

    /* 강의 차시정보 하단테이블 비동기 처리*/
    public List<LectNthDto> getFindLectId(Long lectId) {
        return lectNthRepository.findLectIdInfo(lectId);
    }

    /* 강의 차시정보 하단 우측 3번째 테이블  */
    public List<LmsContsDto> getFindContsNo(Long nthId) {
        return lectNthRepository.findContsNo(nthId);
    }
    // 온라인 강의 조회
    public List<LectNthDto> getFindLectInfo2() {
        return adminLectInfoRepository.findLectInfo2();
    }

    /* 하단 우측 3번째 테이블 create (저장) */
    @Transactional
    public void createLectNth(LectNthDto lectNthDto) {
        log.info("getLectId : " + lectNthDto.getLectId());
        LectInfo lectInfo = adminLectInfoRepository.findById(lectNthDto.getLectId())
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));
        log.info("lectinfo : {}", lectInfo.toString());


        LmsConts lmsConts = lmsContsRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));

        lectNthDto.setNthName(lectNthDto.getNthName());
        lectNthDto.setNthSequence(lectNthDto.getNthSequence());
        lectNthDto.setLectInfo(lectInfo);
        lectNthDto.setLmsConts(lmsConts);
        lectNthRepository.save(lectNthDto.toEntity());
    }

    /* 하단 우측 3번째 테이블 update (수정) */
    @Transactional
    public void updateLectNth(LectNthDto lectNthDto) {

        LectInfo lectInfo = adminLectInfoRepository.findById(lectNthDto.getLectId())
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));
        log.info("lectInfo : " + lectInfo.getLectName());

        LectNth existingLectNth = lectNthRepository.findById(lectNthDto.getNthId())
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));

        existingLectNth.setNthName(lectNthDto.getNthName());
        existingLectNth.setNthSequence(lectNthDto.getNthSequence());
        existingLectNth.setLmsConts(lectNthDto.getLmsConts());
    }

    /* 삭제 기능 구현 */
    @Transactional
    public void deleteLectNth(Long nthId) {
        LectNth lectNth = lectNthRepository.findById(nthId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        lectNthRepository.delete(lectNth);
    }
}