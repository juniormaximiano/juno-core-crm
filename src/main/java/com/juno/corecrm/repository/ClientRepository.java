package com.juno.corecrm.repository;

import com.juno.corecrm.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByClientNameIsContainingIgnoreCase(String clientName);

    List<Client> findByCompanyNameIsContainingIgnoreCase(String companyName);

    List<Client> findAllByActiveTrue();


}
