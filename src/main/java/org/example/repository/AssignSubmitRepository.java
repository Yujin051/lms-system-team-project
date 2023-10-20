package org.example.repository;

import org.example.entity.AssignSubmit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignSubmitRepository extends JpaRepository<AssignSubmit, Long> {

    // 특정 과제에 대한 과제 제출 목록을 가져오는 메서드
//    List<AssignSubmit> findByAssignmentsId(Long assignmentsId);
//
//    // 특정 강의에 대한 과제 제출 목록을 가져오는 메서드
//    List<AssignSubmit> findByAssignmentsLectInfoLectId(Long lectId);
//
//    List<AssignSubmit> findByMemberLectInfoIdAndMemberAssiId(Long lectInfoId, Long assiId);
}