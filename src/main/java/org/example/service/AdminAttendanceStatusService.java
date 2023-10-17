package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.LectInfoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAttendanceStatusService {

    private final LectInfoRepository lectInfoRepository;


 /*   public List<AttendanceStatusDto> attendanceStatusList() {
        return adminAttendanceStatus.findLectInfoDtos();
    }*/

/*    public List<LectInfo> infoList() {
        return adminAttendanceStatus.findByStudLectApply_Id();
    }*/
}
