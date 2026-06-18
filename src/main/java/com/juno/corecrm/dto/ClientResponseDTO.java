package com.juno.corecrm.dto;

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



