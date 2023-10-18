package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.LectInfo;
import org.example.entity.Professor;
import org.example.repository.LectInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 임승범
 */
@RequiredArgsConstructor
@Service
public class LectInfoService {

    private final LectInfoRepository lectInfoRepository;

    public List<LectInfo> getLectInfoList(Professor professor){

        List<LectInfo> lectInfoList = lectInfoRepository.findByProfessor(professor);

        return lectInfoList;
    }

}
