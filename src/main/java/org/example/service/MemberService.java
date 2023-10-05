package org.example.service;

import org.example.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.example.repository.MemberRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 테스트용 경로 설정, 후에 전환 필요
    @Value("C:\\Users\\admin\\IdeaProjects\\lms-system-team-project2\\src\\main\\resources\\static\\img\\profile\\")
    String imgPath;

    public String getFullPath(String filename) {
        return imgPath + filename;
    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    // 아이디 중복가입 여부 확인 후 중복이 아닐 경우 DB에 저장
    public void saveMember(Member member, MultipartFile file) throws Exception {
        validateDuplicateMember(member);

        // 원본 파일명
        String originalFilename = file.getOriginalFilename();

        // 서버에 저장된 파일명
        // 파일명이 중복될 수 있으므로 UUID로 설정
        String savedFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        // 파일 저장
        file.transferTo(new File(getFullPath(savedFilename)));

        // 오리지널 이미지 이름 저장
        member.setImgOriginal(originalFilename);

        // DB 이미지 이름 저장
        member.setImgSaved(savedFilename);
        memberRepository.save(member);
    }

    // 아이디 중복가입 여부 확인 메소드
    public void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUserId(member.getUserId());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 아이디입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
       Member member = memberRepository.findByUserId(userId);
       if(member == null) {
           throw new UsernameNotFoundException(userId);
       }
       return User.builder()
               .username(member.getUserId())
               .password(member.getUserPassword())
               .roles(member.getUserRole().toString())
               .build();
    }

    public List<Member> memberList() {
        return memberRepository.findAll();
    }

    public Member memberView(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public void memberDelete(Long id) {
        memberRepository.deleteById(id);
    }




    public void updateMember(Member member, MultipartFile file) throws Exception {
        // 원본 파일명
        String originalFilename = file.getOriginalFilename();

        // 서버에 저장된 파일명
        // 파일명이 중복될 수 있으므로 UUID로 설정
        String savedFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        // 파일 저장
        file.transferTo(new File(getFullPath(savedFilename)));

        // 오리지널 이미지 이름 저장
        member.setImgOriginal(originalFilename);

        // DB 이미지 이름 저장
        member.setImgSaved(savedFilename);

    }



}
