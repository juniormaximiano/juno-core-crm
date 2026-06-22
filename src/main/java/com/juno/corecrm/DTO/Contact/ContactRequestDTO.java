package com.juno.corecrm.DTO.Contact;

public record ContactRequestDTO(
        String name,
        String role,
        String email,
        String phone
) {
}
