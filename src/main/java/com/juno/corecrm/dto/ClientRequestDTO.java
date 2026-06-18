package com.juno.corecrm.dto;

public record ClientRequestDTO(

        String clientName,

        String companyName,

        String email,

        String phone
) {
}
