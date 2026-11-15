package com.juno.corecrm.DTO.Contact;

public record ContactResponseDTO(
        Long idContact,
        String name,
        String role,
        String email,
        String phone,
        boolean active,
        Long idCompany
) {
}
