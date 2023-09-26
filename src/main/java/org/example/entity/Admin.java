package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.constant.Gender;
import org.example.constant.RoleType;
import org.example.dto.AdminDto;
import org.example.dto.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Table(name= "admin")
@Getter
@Setter
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "admin_id", nullable = false, unique = true)
    private String adminId;

    @Column(name = "admin_pw")
    private String adminPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "admin_role", nullable = false)
    private RoleType userRole;

    @Builder
    public Admin(String adminId, String adminPassword, RoleType userRole) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.userRole = userRole;
    }

    public static Admin createAdmin(AdminDto adminDto , PasswordEncoder passwordEncoder) {
        Admin admin = Admin.builder()
                .adminId(adminDto.getAdminId())
                .adminPassword(passwordEncoder.encode(adminDto.getAdminPassword()))
                .userRole(RoleType.ADMIN)
                .build();
        return admin;
    }
}


