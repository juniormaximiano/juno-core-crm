package com.juno.corecrm.service;

import com.juno.corecrm.domain.entity.Company;
import com.juno.corecrm.dto.company.CompanyRequestDTO;
import com.juno.corecrm.dto.company.CompanyResponseDTO;
import com.juno.corecrm.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyService companyservice;

    @Test
    void createCompany() {
        when(companyRepository.save(any(Company.class))).thenAnswer(inv -> {
            Company saved = inv.getArgument(0);
            saved.setIdCompany(1L);
            return saved;
        });

        CompanyRequestDTO req = new CompanyRequestDTO("Junior", "Maximiano Lab", "maximius@gmail.com", "4140028922");
        CompanyResponseDTO res = companyservice.createCompany(req);
        assertEquals("Maximiano Lab", res.companyName());
        assertEquals("Junior", res.clientName());
        assertEquals("4140028922", res.phone());
        assertEquals("maximius@gmail.com", res.email());
        assertTrue(res.activeCompany());
        verify(companyRepository).save(any(Company.class));


    }
}
