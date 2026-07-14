package com.juno.corecrm.DTO.Company;

public record CompanyRequestDTO(

        String clientName,

        String companyName,

        String email,

        String phone
) {
}
