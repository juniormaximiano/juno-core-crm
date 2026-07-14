package com.juno.corecrm.service;

import com.juno.corecrm.domain.entity.Company;
import com.juno.corecrm.DTO.Company.CompanyRequestDTO;
import com.juno.corecrm.DTO.Company.CompanyResponseDTO;
import com.juno.corecrm.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();

        for (Company company : allCompanies) {
            companyResponseDTOS.add(convertToDTO(company));
        }

        return companyResponseDTOS;
    }

    public CompanyResponseDTO findCompanyById(Long id) {

        Company searchedCompany = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Company not found"
                        )
                );

        return convertToDTO(searchedCompany);
    }

    public CompanyResponseDTO updateCompany(
            Long id,
            CompanyRequestDTO request
    ) {

        Company searchedCompany = companyRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Company not found"
                        )
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
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Company not found"
                        )
                );

        searchedCompany.setActive(false);

        Company updatedCompany = companyRepository.save(searchedCompany);

        return convertToDTO(updatedCompany);
    }

    public List<CompanyResponseDTO> findCompanyByClientName(String name) {

        List<Company> searchedCompanies =
                companyRepository.findByClientNameContainingIgnoreCase(name);

        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();

        for (Company company : searchedCompanies) {
            companyResponseDTOS.add(convertToDTO(company));
        }

        return companyResponseDTOS;
    }

    public List<CompanyResponseDTO> findByCompanyName(String companyName) {

        List<Company> searchedCompanies =
                companyRepository.findByCompanyNameContainingIgnoreCase(companyName);

        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();

        for (Company company : searchedCompanies) {
            companyResponseDTOS.add(convertToDTO(company));
        }

        return companyResponseDTOS;
    }

    public List<CompanyResponseDTO> findActiveCompanies() {

        List<Company> activeCompanies =
                companyRepository.findAllByActiveTrue();

        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();

        for (Company company : activeCompanies) {
            companyResponseDTOS.add(convertToDTO(company));
        }

        return companyResponseDTOS;
    }
}
