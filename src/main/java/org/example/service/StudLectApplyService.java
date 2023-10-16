package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.ClassMateDto;
import org.example.entity.LectInfo;
import org.example.entity.Member;
import org.example.entity.StudLectApply;
import org.example.entity.Student;
import org.example.repository.LectInfoRepository;
import org.example.repository.MemberRepository;
import org.example.repository.StudLectApplyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudLectApplyService {

    private final StudLectApplyRepository studLectApplyRepository;
    private final LectInfoRepository lectInfoRepository;
    private final MemberRepository memberRepository;

    // 학생정보로 수강신청한 레코드 리스트 가져오기
    public List<StudLectApply> getStudLectApply(Student student){

        List<StudLectApply> article = studLectApplyRepository.findByStudent(student);

        return article;
    }

    // 강의번호 하나로 강의 참가자들 조회해서 회원리스트 반환
    public List<ClassMateDto> findClassmate(ClassMateDto classMateDto){

        Long id = classMateDto.getClassId();

        // 수업 참가 학생 리스트 가져오기
        List<Student> students = studLectApplyRepository.findClassMate(id);
        log.info("students::{}" , students);
        // 회원 리스트에 각 학생들 회원정보 찾아 담기.
        List<Member> memberList = new ArrayList<>();
        for (Student student : students) {
            Long memberId = student.getMember().getId();
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(()-> new IllegalArgumentException("Not found : " + memberId + "로 Member 가져오기 불가"));
            if(member != null){
                memberList.add(member);
            }
        }
        log.info("memberList::{}" , memberList);
        // 수업 정보 가져오기
        LectInfo lectInfo = lectInfoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not found : " + id + " 로 LectInfo 가져오기 불가"));
        log.info("lectInfo::{}",lectInfo);

        // 수업정보의 강사id 외래키를 이용하여 회원정보를 찾아 가져오기
        Long profMemberId = lectInfo.getProfessor().getMember().getId();
        Member prof = memberRepository.findById(profMemberId)
                .orElseThrow(()-> new IllegalArgumentException("Not found : " + profMemberId + "로 Member 가져오기 불가"));

        log.info("prof::{}",prof);
        memberList.add(prof);

        log.info("서비스 마지막 memberList = " + memberList);

        List<ClassMateDto> classmates = new ArrayList<>();
        // 생성자를 이용하여 필요한 데이터만 dto에 넣어주고 반환시킨다.
        for(int i=0; i<memberList.toArray().length; i++){
            Member member = memberList.get(i);
            ClassMateDto classmate = new ClassMateDto(
                    classMateDto.getClassId() ,
                    member.getUserName() ,
                    member.getId(),
                    member.getUserId());
            classmates.add(classmate);
        }

        return classmates;
    }



}
