package org.example.service.admin;

import lombok.RequiredArgsConstructor;
import org.example.dto.admin.LectureListDto;
import org.example.entity.LectInfo;
import org.example.repository.LectureRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    // 전체 강좌 리스트 반환
    public JSONObject findAllLecture() {
        List<LectureListDto> lectureList = lectureRepository.findAllLectList();
        JSONObject object = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject pagination = new JSONObject();
        object.put("result", true);
        object.put("data", data);
        data.put("contents", lectureList);
        data.put("pagination", pagination);
        return object;
    }

}
