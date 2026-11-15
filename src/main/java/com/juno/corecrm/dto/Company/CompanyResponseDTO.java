package com.juno.corecrm.DTO.Company;

import java.time.LocalDate;

public record CompanyResponseDTO(

        long idClient,

        String clientName,

        String companyName,

        String email,

        String phone,

        boolean activeClient,

        LocalDate createdAt
) {
}



