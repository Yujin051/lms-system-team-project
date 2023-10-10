package org.example.dto;

import lombok.*;
import org.example.entity.Member;
import org.example.entity.Professor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ProfessorDto {
    private Long profId;
    private Long memberId;
    private String profAgency;
    private String profWork;
    private String profBank;
    private String profAccount;
    private boolean isActive;

    @Builder
    public ProfessorDto(Long profId, Long memberId ,String profAgency, String profWork, String profBank, String profAccount, boolean isActive) {
        this.profId = profId;
        this.memberId = memberId;
        this.profAgency = profAgency;
        this.profWork = profWork;
        this.profBank = profBank;
        this.profAccount = profAccount;
        this.isActive = isActive;
    }

//    public List<Professor> ProfessorDtoToListEntity(List<Professor> professor) {
//        return professor.stream()
//                .map(Professor::toEntity)
//                .collect(Collectors.toList());
//    }




}