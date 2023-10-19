package org.example.service.admin;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.admin.LmsContsDto;
import org.example.entity.LectInfo;
import org.example.entity.LectNth;
import org.example.entity.LmsConts;
import org.example.repository.LectNthRepository;
import org.example.repository.LmsContsRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LmsContsService {

    private final LmsContsRepository lmsContsRepository;
    private final LectNthRepository lectNthRepository;

    public List<LmsContsDto> findAllConts() {
        // Dto 객체 선언
        List<LmsContsDto> lmsContsDtos = new ArrayList<>();

        // 컨텐츠 전체 목록 조회
        List<LmsConts> lmsContsList = lmsContsRepository.findAll();

        // 반복문으로 전달할 Dto 객체 생성
        for (LmsConts lmsConts : lmsContsList) {

            // 각각의 요소 추출
            Long id = lmsConts.getContsNo();
            String name = lmsConts.getContsName();
            String detail = lmsConts.getContsDetail();
            int time = lmsConts.getContsTime();
            String youtube = lmsConts.getContsYout();

            // 강좌 찾기, Optional 이용해서 null 체크
            Optional<LectNth> lectNthOptional = lectNthRepository.findByLmsConts(lmsConts);

            // lectInfo 를 찾지 못했다면 강좌가 지정되지 않은( 강좌 이름이 없는) 객체 전달
            // 찾았다면 강좌가 지정된(강좌 이름이 있는) 객체 전달

            if (lectNthOptional.isPresent()) {
                LectInfo lectInfo = lectNthOptional.get().getLectInfo();

                String lectName = lectInfo.getLectName();

                LmsContsDto lmsContsDto = LmsContsDto.builder()
                        .contsNo(id)
                        .contsName(name)
                        .contsDetail(detail)
                        .contsTime(time)
                        .contsYout(youtube)
                        .lectName(lectName)
                        .build();

                lmsContsDtos.add(lmsContsDto);
            } else {
                LmsContsDto lmsContsDto = LmsContsDto.builder()
                        .contsNo(id)
                        .contsName(name)
                        .contsDetail(detail)
                        .contsTime(time)
                        .contsYout(youtube)
                        .build();

                lmsContsDtos.add(lmsContsDto);
            }
        }
        return lmsContsDtos;
    }

    // 전체 컨텐츠 목록 뿌리는 리스트
    public JSONObject findAllContsToJson() {
        // Dto 객체 선언
        List<LmsContsDto> lmsContsDtos = this.findAllConts();

        // 리스트를 datasource 사용을 위해 JSONObject 변환
        JSONObject object = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject pagination = new JSONObject();
        object.put("result", true);
        object.put("data", data);
        data.put("contents", lmsContsDtos);
        data.put("pagination", pagination);
        return object;
    }

    // 검색 서비스
    public List<LmsContsDto> searchConts(String contsName, String lectName) {
        // 검색하는 서비스
        return lmsContsRepository.searchByContsNameAndLectName(contsName, lectName);
    }


    // 하나의 컨텐츠 상세정보 얻어오기
    public LmsContsDto getDetail(Long contsNo) {
        LmsConts lmsConts = lmsContsRepository.findById(contsNo)
                .orElseThrow(EntityNotFoundException::new);

        Long id = lmsConts.getContsNo();
        String name = lmsConts.getContsName();
        String detail = lmsConts.getContsDetail();
        String youtube = lmsConts.getContsYout();
        int time = lmsConts.getContsTime();

        return LmsContsDto.builder()
                .contsNo(id)
                .contsName(name)
                .contsDetail(detail)
                .contsYout(youtube)
                .contsTime(time)
                .build();
    }


    // 컨텐츠 정보 업데이트하기
    @Transactional
    public void updateConts(Long contsNo, String title, String detail) {

        LmsConts lmsConts = lmsContsRepository.findById(contsNo)
                .orElseThrow(EntityNotFoundException::new);

        // JPA 더티체킹
        lmsConts.setContsName(title);
        lmsConts.setContsDetail(detail);

        lmsContsRepository.save(lmsConts);
    }

    @Transactional
    // 총 재생시간 갱신
    public void updateTime(String videoId, int duration) {
        LmsConts lmsConts = lmsContsRepository.findByContsYout(videoId);

        // 더티체킹
        lmsConts.setContsTime(duration);

        lmsContsRepository.save(lmsConts);
    }

    // 컨텐츠 삭제
    @Transactional
    public void deleteConts(String videoId) {
        LmsConts lmsConts = lmsContsRepository.findByContsYout(videoId);
        lmsContsRepository.delete(lmsConts);
    }
}
