package com.juno.corecrm.repository;

import com.juno.corecrm.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAll();
}
