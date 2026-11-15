package com.juno.corecrm.dto.Contact;

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
