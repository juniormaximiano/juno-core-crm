package com.juno.corecrm.service;

import com.juno.corecrm.domain.entity.Company;
import com.juno.corecrm.dto.company.CompanyRequestDTO;
import com.juno.corecrm.dto.company.CompanyResponseDTO;
import com.juno.corecrm.exception.ResourceNotFoundException;
import com.juno.corecrm.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponseDTO convertToDTO(Company company) {
        return new CompanyResponseDTO(
                company.getIdCompany(),
                company.getClientName(),
                company.getCompanyName(),
                company.getEmail(),
                company.getPhone(),
                company.isActive(),
                company.getCreatedAt()
        );
    }

    public CompanyResponseDTO createCompany(CompanyRequestDTO dto) {

        Company newCompany = new Company();

        newCompany.setClientName(dto.clientName());
        newCompany.setCompanyName(dto.companyName());
        newCompany.setEmail(dto.email());
        newCompany.setPhone(dto.phone());
        newCompany.setActive(true);
        newCompany.setCreatedAt(LocalDate.now());

        Company createdCompany = companyRepository.save(newCompany);

        return convertToDTO(createdCompany);
    }

    public List<CompanyResponseDTO> getAllCompanies() {

        List<Company> allCompanies = companyRepository.findAll();

        return allCompanies.stream().map(this::convertToDTO).toList();
    }

    public CompanyResponseDTO findCompanyById(Long id) {

        Company searchedCompany = companyRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Company not found")
                );

        return convertToDTO(searchedCompany);
    }

    public CompanyResponseDTO updateCompany(
            Long id,
            CompanyRequestDTO request
    ) {

        Company searchedCompany = companyRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Company not found")
                );

        searchedCompany.setClientName(request.clientName());
        searchedCompany.setCompanyName(request.companyName());
        searchedCompany.setEmail(request.email());
        searchedCompany.setPhone(request.phone());

        Company updatedCompany = companyRepository.save(searchedCompany);

        return convertToDTO(updatedCompany);
    }

    public CompanyResponseDTO deactivateCompany(Long id) {

        Company searchedCompany = companyRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Company not found")
                );

        searchedCompany.setActive(false);

        Company updatedCompany = companyRepository.save(searchedCompany);

        return convertToDTO(updatedCompany);
    }

    public List<CompanyResponseDTO> findCompanyByClientName(String name) {

        List<Company> searchedCompanies =
                companyRepository.findByClientNameContainingIgnoreCase(name);

        return searchedCompanies.stream().map(this::convertToDTO).toList();
    }

    public List<CompanyResponseDTO> findByCompanyName(String companyName) {

        List<Company> searchedCompanies =
                companyRepository.findByCompanyNameContainingIgnoreCase(companyName);

        return searchedCompanies.stream().map(this::convertToDTO).toList();
    }

    public List<CompanyResponseDTO> findActiveCompanies() {

        List<Company> activeCompanies =
                companyRepository.findAllByActiveTrue();

        return activeCompanies.stream().map(this::convertToDTO).toList();
    }
}
