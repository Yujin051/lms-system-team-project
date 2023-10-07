package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.BoardInfo;
import org.example.repository.BoardInfoRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardInfoService {

    private final BoardInfoRepository boardInfoRepository;

    // 게시판 하나 id로 가져오기
    public BoardInfo findById(Long id){
        return boardInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found : BoardInfo의 " + id + "를 이용해 데이터 찾기 불가."));
    }
}
