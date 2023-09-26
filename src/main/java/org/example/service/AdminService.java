package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entity.Admin;
import org.example.entity.Member;
import org.example.repository.AdminRepository;
import org.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;

    // 아이디 중복가입 여부 확인 후 중복이 아닐 경우 DB에 저장
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByAdminId(adminId);
        if(admin == null) {
            throw new UsernameNotFoundException(adminId);
        }
        return User.builder()
                .username(admin.getAdminId())
                .password(admin.getAdminPassword())
                .roles(admin.getUserRole().toString())
                .build();
    }

    public List<Admin> memberList() {
        return adminRepository.findAll();
    }



    public Admin updateMember(Admin admin) {
        return adminRepository.save(admin);
    }

    public void memberDelete(Long id) {
        adminRepository.deleteById(id);
    }
}
