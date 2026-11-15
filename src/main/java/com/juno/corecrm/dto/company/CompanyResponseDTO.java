package com.juno.corecrm.dto.company;

import java.time.LocalDate;

public record CompanyResponseDTO(

        long idCompany,

        String clientName,

        String companyName,

        String email,

        String phone,

        boolean activeCompany,

        LocalDate createdAt
) {
}



