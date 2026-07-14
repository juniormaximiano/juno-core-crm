package com.juno.corecrm.repository;

import com.juno.corecrm.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByClientNameContainingIgnoreCase(String name);

    List<Company> findByCompanyNameContainingIgnoreCase(String name);

    List<Company> findAllByActiveTrue();


}
