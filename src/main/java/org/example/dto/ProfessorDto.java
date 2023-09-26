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

    @Builder
    public ProfessorDto(String profAgency, String profWork, String profBank, String profAccount) {
        this.profAgency = profAgency;
        this.profWork = profWork;
        this.profBank = profBank;
        this.profAccount = profAccount;
    }
}
