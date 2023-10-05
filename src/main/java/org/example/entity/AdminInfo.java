package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.constant.RoleType;

/**
 * 관리자정보 엔티티
 * @author 임휘재
 */
@Entity
@Data
@Table(name = "admin_info")
public class AdminInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "admin_pwd")
    private String adminPwd;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

}
