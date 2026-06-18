package com.juno.corecrm.repository;

import com.juno.corecrm.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
