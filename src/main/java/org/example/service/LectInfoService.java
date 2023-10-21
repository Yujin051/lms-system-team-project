package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.board.ClassListDto;
import org.example.entity.LectInfo;
import org.example.entity.Professor;
import org.example.repository.LectInfoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 임승범
 */
@RequiredArgsConstructor
@Service
public class LectInfoService {

    private final LectInfoRepository lectInfoRepository;

    public List<ClassListDto> getLectInfoList(Professor professor){

        List<LectInfo> lectInfoList = lectInfoRepository.findByProfessor(professor);

        List<ClassListDto> classListDtos = new ArrayList<>();

        lectInfoList.forEach(lectInfo -> {
            classListDtos.add(new ClassListDto(lectInfo.getLectId() , lectInfo.getLectName()));
        });

        return classListDtos;
    }

}
