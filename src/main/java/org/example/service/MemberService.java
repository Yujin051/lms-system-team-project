package org.example.service;

import org.example.entity.LectInfo;
import org.example.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.repository.LectInfoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.example.repository.MemberRepository;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 아이디 중복가입 여부 확인 후 중복이 아닐 경우 DB에 저장
    public void saveMember(Member member) {
        validateDuplicateMember(member);
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



}
