package com.juno.corecrm.DTO.Client;

import java.time.LocalDate;

public record ClientResponseDTO(

        long idClient,

        String clientName,

        String companyName,

        String email,

        String phone,

        boolean activeClient,

        LocalDate createdAt
) {
}



