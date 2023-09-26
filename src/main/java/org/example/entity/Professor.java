package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.MemberFormDto;
import org.example.dto.ProfessorDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.swing.*;

@NoArgsConstructor
@AllArgsConstructor
@Table(name= "teacher")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Professor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prof_id", updatable = false)
    private Long id;

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

    @Builder
    public Professor(Member member, String profAgency, String profBank, String profWork, String profAccount) {
        this.member = member;
        this.profBank = profBank;
        this.profWork = profWork;
        this.profAgency = profAgency;
        this.profAccount = profAccount;
    }

    public static Professor createProfessor(ProfessorDto professorDto, Member member) {
        Professor professor = Professor.builder()
                .member(member)
                .profBank(professorDto.getProfBank())
                .profWork(professorDto.getProfWork())
                .profAgency(professorDto.getProfAgency())
                .profAccount(professorDto.getProfAccount())
                .build();
        return professor;
    }
}
