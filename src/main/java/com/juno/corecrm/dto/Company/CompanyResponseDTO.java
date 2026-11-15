package com.juno.corecrm.dto.Company;

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



