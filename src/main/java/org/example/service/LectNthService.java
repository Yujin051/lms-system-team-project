package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.LectNthDto;
import org.example.dto.LmsContsDto;
import org.example.dto.LmsContsRequestDto;
import org.example.entity.LectNth;
import org.example.repository.AdminThisTimeRegisTration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectNthService {

    private final AdminThisTimeRegisTration adminThisTimeRegisTration;

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


    /* 강의 차시정보 하단 우측 3번째 테이블  */
    public List<LmsContsDto> getFindContsNo(Long contsNo) {
        return adminThisTimeRegisTration.findContsNo(contsNo);
    }




    /* 하단 우측 3번째 테이블 update (저장) */
    @Transactional
    public LectNth createLectNth(LmsContsRequestDto lmsContsRequestDto) {
        LectNth lectNth = adminThisTimeRegisTration.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException(" 게시판 정보를 찾을 수 없어"));
        lmsContsRequestDto.setLectNth(lectNth);
        return adminThisTimeRegisTration.save(lmsContsRequestDto.toEntity());


    }

    /* 하단 우측 3번째 테이블 update (수정) */
    @Transactional
    public LectNth updateLectNth(LmsContsRequestDto lmsContsRequestDto) {
        LectNth lectNth = adminThisTimeRegisTration.findById(lmsContsRequestDto.getNthId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없어"));
        /* 수정 필드 업뎃*/
        lectNth.setNthSequence(lmsContsRequestDto.getNthSequence());
        lectNth.setNthName(lmsContsRequestDto.getNthName());
        lectNth.setLmsConts(lmsContsRequestDto.getLmsConts());
        return lmsContsRequestDto.save(lectNth);
    }
}
