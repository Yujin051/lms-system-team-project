package org.example.service.admin;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProfessorDto;
import org.example.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminProfInfoService {

    private final ProfessorRepository professorRepository;
    
    // 전체 조회
    public List<ProfessorDto> professorList() {
        return new ArrayList<>(professorRepository.findProfessorDtos());
    }
    
    //다중 조건 조회
    public List<ProfessorDto> professorConditionList(String active, String subject, String name) {

        boolean isActive = Boolean.parseBoolean(active);
        return new ArrayList<>(professorRepository.findProfessorDtosByConditions(isActive, subject, name));
    }

    public ProfessorDto professorDetails(String profWork, String userName) {
        return professorRepository.findProfessorDetail(profWork, userName);
    }


}
