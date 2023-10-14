package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.LectPlan;
import org.example.repository.LectPlanRepository;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
@Setter
public class LectPlanService {


    private final LectPlanRepository lectPlanRepository;

    public LectPlan lectPlanView(long id) {
        return lectPlanRepository.findById(id).get();
    }
}
