package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.service.LectPlanService;
import org.example.service.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Getter
@Setter
@AllArgsConstructor
public class LectureController {


    private final LectPlanService lectPlanService;


    @GetMapping("/student/lecture/view/{id}/lectureplan")
    public String viewLecturePlan(Model model, @PathVariable("id")long id) {
        model.addAttribute("lectplan", lectPlanService.lectPlanView(id));
        return "/student/lecturePlan";
    }
}
