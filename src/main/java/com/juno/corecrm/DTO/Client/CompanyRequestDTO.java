package com.juno.corecrm.DTO.Client;

public record CompanyRequestDTO(

        String clientName,

        String companyName,

        String email,

        String phone
) {
}
