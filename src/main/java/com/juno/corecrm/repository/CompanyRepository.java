package com.juno.corecrm.repository;

import com.juno.corecrm.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByContactNameContainingIgnoreCase(String name);

    List<Company> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Company> findAllByActiveTrue();


}
