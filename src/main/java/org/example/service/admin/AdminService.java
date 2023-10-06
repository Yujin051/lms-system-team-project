package org.example.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.admin.MemberDto;
import org.example.repository.admin.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

    //관리자 - 학생관리 : 학생정보
    public List<MemberDto> getStudentInfo(){
        return adminRepository.findMembersWithDto();
    }

    //관리자 - 학생관리 : 기본정보
    public List<MemberDto> getBasicInfo(){
        return adminRepository.findMemberDtoOne();
    }

    //관리자 - 학생관리 : 평균학점
    public MemberDto getStudCreCplAvg(){
        return adminRepository.findStudCreCplAvg();
    }

    //관리자 - 학생관리 : 이름검색
    public List<MemberDto> getFindUserNameContaining(String keyword){
        return adminRepository.findByUserNameContainingIgnoreCase(keyword);
    }

    //관리자 - 학생관리 : 학번검색
    public List<MemberDto> getFindByStudId(Long Keyword){
        return adminRepository.findByStudId(Keyword);
    }

    //관리자 - 학생관리 : 이름, 학번 검색
    public List<MemberDto> getFindByStudIdAndUserName(Long idKeyword, String nameKeyword){
        return adminRepository.findByStudIdAndUserName(idKeyword, nameKeyword);
    }

    //관리자 - 학생관리 : 검색어가 비어있을때 조회버튼 누르면 전체 조회
    public List<MemberDto> getNoSearch(Long idKeyword, String nameKeyword){
        return adminRepository.noSearchByNameAndStudId(nameKeyword, idKeyword);
    }

    //관리자 - 학생관리 : 검색어가 비어있을때 조회버튼 누르면 전체 조회
    public MemberDto getFindMemberInfo(Long memberId){
        log.info("serviceMemberId : " + memberId);
        return adminRepository.findMemberInfo(memberId);
    }
}
