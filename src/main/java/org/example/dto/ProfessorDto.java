package org.example.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
public class ProfessorDto {


    private String profAgency;
    private String profWork;
    private String profBank;
    private String profAccount;
    private boolean isActive;

    @Builder
    public ProfessorDto(String profAgency, String profWork, String profBank, String profAccount, boolean isActive) {
        this.profAgency = profAgency;
        this.profWork = profWork;
        this.profBank = profBank;
        this.profAccount = profAccount;
        this.isActive = isActive;
    }
}