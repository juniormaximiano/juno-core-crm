package com.juno.corecrm.service;

import com.juno.corecrm.dto.contact.ContactRequestDTO;
import com.juno.corecrm.dto.contact.ContactResponseDTO;
import com.juno.corecrm.domain.entity.Company;
import com.juno.corecrm.domain.entity.Contact;
import com.juno.corecrm.exception.ResourceNotFoundException;
import com.juno.corecrm.repository.CompanyRepository;
import com.juno.corecrm.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ContactService {

  private final  CompanyRepository companyRepository;

  private final  ContactRepository contactRepository;

    public ContactService(CompanyRepository companyRepository, ContactRepository contactRepository) {
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
    }

    public ContactResponseDTO createContact(Long id, ContactRequestDTO request) {
        Company searchedCompany = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        Contact contact = new Contact();
        contact.setName(request.name());
        contact.setEmail(request.email());
        contact.setPhone(request.phone());
        contact.setRole(request.role());
        contact.setActive(true);
        contact.setCompany(searchedCompany);
        Contact savedContact = contactRepository.save(contact);

        return convertToDTO(savedContact);

    }

    public ContactResponseDTO convertToDTO(Contact contact) {
        return new ContactResponseDTO(
                contact.getId(),
                contact.getName(),
                contact.getRole(),
                contact.getEmail(),
                contact.getPhone(),
                contact.isActive(),
                contact.getCompany().getIdCompany()
        );
    }

    public ContactResponseDTO findContactById(Long id) {
        Contact contactSearched = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found")
                );

        return convertToDTO(contactSearched);
    }

    public ContactResponseDTO updateContact(Long id, ContactRequestDTO request) {

        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        contact.setId(id);

        contact.setName(request.name());

        contact.setEmail(request.email());

        contact.setPhone(request.phone());

        contact.setRole(request.role());

        Contact updatedContact = contactRepository.save(contact);

        return convertToDTO(updatedContact);

    }

    public ContactResponseDTO deactivateContact(Long id) {

        Contact contactSearched = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        contactSearched.setActive(false);

        Contact updatedContact = contactRepository.save(contactSearched);


        return convertToDTO(updatedContact);


    }

    public List<ContactResponseDTO> listAllContacts() {

        var allContacts = contactRepository.findAll();

        return allContacts.stream().map(this::convertToDTO).toList();
    }

    public ContactResponseDTO findById(Long id) {

        Contact contactSearched = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        return convertToDTO(contactSearched);

    }


}
