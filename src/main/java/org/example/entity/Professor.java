package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.ProfessorDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Table(name= "professor")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prof_id", updatable = false)
    private Long profId;

    @OneToOne
    @JoinColumn(name= "member_id")
    private Member member;

    @Column(name= "prof_agency")
    private String profAgency;

    @Column(name= "prof_work")
    private String profWork;

    @Column(name= "prof_bank")
    private String profBank;

    @Column(name = "prof_account")
    private String profAccount;

    @Column(name = "is_active")
    private boolean isActive;

    @Builder
    public Professor(Member member, String profAgency, String profBank, String profWork, String profAccount, boolean isActive) {
        this.member = member;
        this.profBank = profBank;
        this.profWork = profWork;
        this.profAgency = profAgency;
        this.profAccount = profAccount;
        this.isActive = isActive;
    }

    public static Professor createProfessor(ProfessorDto professorDto, Member member) {
        Professor professor = Professor.builder()
                .member(member)
                .profBank(professorDto.getProfBank())
                .profWork(professorDto.getProfWork())
                .profAgency(professorDto.getProfAgency())
                .profAccount(professorDto.getProfAccount())
                .isActive(professorDto.isActive())
                .build();
        return professor;
    }
}