package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AddLmsContsRequestDto;
import org.example.dto.LectNthDto;
import org.example.dto.LmsContsDto;
import org.example.entity.LectInfo;
import org.example.entity.LectNth;
import org.example.entity.LmsConts;
import org.example.repository.AdminAttendanceStatus;
import org.example.repository.AdminThisTimeRegisTration;
import org.example.repository.LmsContsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectNthService {

    private final AdminThisTimeRegisTration adminThisTimeRegisTration;
    private final LmsContsRepository lmsContsRepository;
    private final AdminAttendanceStatus adminAttendanceStatus;

    public List<LectNthDto> lectNthList(String searchType, Boolean nthKeyword) {
        return adminThisTimeRegisTration.findLectNthDtos(searchType, nthKeyword);
    }

    /* search */
    @Transactional
    public List<LectNth> search(String keyword) {
        return adminThisTimeRegisTration.findByNthIdContaining(keyword);
    }

    public List<LectNthDto> getLectNthData(String searchType, Boolean nthKeyword) {
        return adminThisTimeRegisTration.findLectNthDtos(searchType, nthKeyword);
    }

    // 온라인 강좌명 조회기능
    public List<LectNthDto> getFindLectNthSearch(String lectName, Boolean isActive) {
        return adminThisTimeRegisTration.findLectNthSearch(lectName, isActive);
    }

    public List<LectNthDto> getFindLectNthBox(Boolean isActive) {
        return adminThisTimeRegisTration.findLectNthBox(isActive);
    }

    public List<LectNthDto> getFindLectName(String lectName) {
        return adminThisTimeRegisTration.findLectName(lectName);
    }
    /* 강의 차시정보 하단테이블 비동기 처리*/
    public List<LectNthDto> getFindLectId(Long lectId) {
        return adminThisTimeRegisTration.findLectIdInfo(lectId);
    }

    public List<LectNthDto> getFindLectInfo() {
        return adminThisTimeRegisTration.findLectInfo();
    }


    /* 강의 차시정보 하단 우측 3번째 테이블  */
    public List<LmsContsDto> getFindContsNo(Long contsNo) {
        return adminThisTimeRegisTration.findContsNo(contsNo);
    }

    public List<LectNthDto> getFindNthId() {
        return adminThisTimeRegisTration.findNthId();
    }



    /* 하단 우측 3번째 테이블 update (저장) */
    @Transactional
    public void createLectNth(LectNthDto lectNthDto) {

        LectNth lectNth = adminThisTimeRegisTration.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));

        LectInfo lectInfo = adminAttendanceStatus.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));
        log.info("serLectId : " + lectInfo.getLectId());

        LmsConts lmsConts = lmsContsRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));

        lectNthDto.setLectNth(lectNth);
        lectNthDto.setLectInfo(lectInfo);
        lectNthDto.setLmsConts(lmsConts);
        adminThisTimeRegisTration.save(lectNthDto.toEntity());


    }

    /* 하단 우측 3번째 테이블 update (수정) */
    @Transactional
    public void updateLectNth(LectNthDto lectNthDto) {

        LectNth existingLectNth = adminThisTimeRegisTration.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));


        existingLectNth.setNthName(lectNthDto.getNthName());
        existingLectNth.setNthSequence(lectNthDto.getNthSequence());
        existingLectNth.setLmsConts(lectNthDto.getLmsConts());
        adminThisTimeRegisTration.save(existingLectNth); // 혹은 수정된 엔티티를 반환하거나 원하는 반환값으로 수정
    }



}
