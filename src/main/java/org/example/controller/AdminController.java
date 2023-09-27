package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Member;
import org.example.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {


    private final MemberRepository memberRepository;

    @GetMapping(value= "/admin")
    public String adminroot(Principal principal, Model model) {
        Member member = memberRepository.findByUserId(principal.getName());
        String name = (member != null) ? member.getUserName() : "Unknown"; // Member 테이블에서 이름 가져오기

        model.addAttribute("name", name);
        return "admin/root";
    }

}
