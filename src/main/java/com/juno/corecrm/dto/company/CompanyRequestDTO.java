package com.juno.corecrm.dto.company;

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
