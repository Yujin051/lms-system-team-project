package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.constant.Gender;
import org.example.constant.RoleType;
import org.example.dto.MemberFormDto;
import org.example.dto.ProfessorDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.service.MemberService;
import org.example.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final ProfessorService professorService;
    private final PasswordEncoder profpasswordEncoder;
    private final PasswordEncoder studentpasswordEncoder;

    @ModelAttribute("userGender")
    public Gender[] userGender() {
        return Gender.values();
    }

    @ModelAttribute("userRole")
    public RoleType[] userRole() {
        return RoleType.values();
    }

    @GetMapping(value = "/newprofessor")
    public String ProfessorForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        model.addAttribute("professorDto", new ProfessorDto());
        return "prof/signup";
    }

    @GetMapping(value = "/newstudent")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "student/signup";
    }


    @PostMapping(value = "newprofessor")
    public String professorForm(@Validated MemberFormDto memberFormDto, @Validated ProfessorDto professorDto, BindingResult bindingResult, Model model, @RequestPart MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "prof/signup";
        }

        try {
            Member member = Member.createProf(memberFormDto, profpasswordEncoder);
            Professor professor = Professor.createProfessor(professorDto, member);

            memberService.saveMember(member, file);
            professorService.saveProfessor(professor);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "prof/signup";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "prof/signupSuccess";
    }

    @PostMapping(value = "newstudent")
    public String studentForm(@Validated MemberFormDto memberFormDto, BindingResult bindingResult, Model model, @RequestPart MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "student/signup";
        }

        try {
            Member member = Member.createStudent(memberFormDto, studentpasswordEncoder);

            memberService.saveMember(member, file);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "student/signup";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "student/signupSuccess";
    }

    @GetMapping(value = "/member/login")
    public String memberLogin() {
        return "/member/login";
    }

    @GetMapping(value = "/member/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요");
        return "/member/login";
    }
}
