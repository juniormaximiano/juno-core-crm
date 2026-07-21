package com.juno.corecrm.DTO.Company;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDTO(


        @NotBlank
        String clientName,

        @NotBlank
        String companyName,

        @NotBlank
        String email,

        @NotBlank
        String phone
) {
}
