package com.juno.corecrm.DTO.Client;

public record ClientRequestDTO(

        String clientName,

        String companyName,

        String email,

        String phone
) {
}
