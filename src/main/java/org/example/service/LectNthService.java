package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.LectNthDto;
import org.example.repository.AdminThidTimeRegisTration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectNthService {

    private final AdminThidTimeRegisTration adminThidTimeRegisTration;

    public List<LectNthDto> lectNthList() {
        return adminThidTimeRegisTration.findLectNthDtos();
    }
}
