package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AttendanceStatusDto;
import org.example.entity.LectInfo;
import org.example.repository.AdminAttendanceStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAttendanceStatusService {

    private final AdminAttendanceStatus adminAttendanceStatus;


 /*   public List<AttendanceStatusDto> attendanceStatusList() {
        return adminAttendanceStatus.findLectInfoDtos();
    }*/

/*    public List<LectInfo> infoList() {
        return adminAttendanceStatus.findByStudLectApply_Id();
    }*/
}
