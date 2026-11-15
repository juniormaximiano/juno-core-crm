package com.juno.corecrm.dto.contact;

import jakarta.validation.constraints.NotBlank;

public record ContactRequestDTO(

        @NotBlank
        String name,

        @NotBlank
        String role,

        @NotBlank
        String email,

        @NotBlank
        String phone
) {
}
