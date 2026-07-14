package com.juno.corecrm.controller;

import com.juno.corecrm.DTO.Company.CompanyRequestDTO;
import com.juno.corecrm.DTO.Company.CompanyResponseDTO;
import com.juno.corecrm.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyResponseDTO createCompany(
            @RequestBody CompanyRequestDTO companyRequestDTO
    ) {
        return companyService.createCompany(companyRequestDTO);
    }

    @GetMapping
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponseDTO getCompanyById(
            @PathVariable Long id
    ) {
        return companyService.findCompanyById(id);
    }

    @PutMapping("/{id}")
    public CompanyResponseDTO updateCompany(
            @PathVariable Long id,
            @RequestBody CompanyRequestDTO companyRequestDTO
    ) {
        return companyService.updateCompany(id, companyRequestDTO);
    }

    @PatchMapping("/{id}/deactivate")
    public CompanyResponseDTO deactivateCompany(
            @PathVariable Long id
    ) {
        return companyService.deactivateCompany(id);
    }

    @GetMapping("/search")
    public List<CompanyResponseDTO> searchCompanies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String companyName
    ) {
        if (name != null) {
            return companyService.findByCompanyName(name);
        }

        if (companyName != null) {
            return companyService.findByCompanyName(companyName);
        }

        return companyService.getAllCompanies();
    }

    @GetMapping("/active")
    public List<CompanyResponseDTO> findAllActiveCompanies() {
        return companyService.findActiveCompanies();
    }
}
