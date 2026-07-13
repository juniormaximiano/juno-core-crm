package com.juno.corecrm.service;

import com.juno.corecrm.DTO.Contact.ContactRequestDTO;
import com.juno.corecrm.DTO.Contact.ContactResponseDTO;
import com.juno.corecrm.domain.entity.Client;
import com.juno.corecrm.domain.entity.Contact;
import com.juno.corecrm.repository.ClientRepository;
import com.juno.corecrm.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactService {

    ClientRepository clientRepository;
    ContactRepository contactRepository;

    public ContactService(ClientRepository clientRepository, ContactRepository contactRepository) {
        this.clientRepository = clientRepository;
        this.contactRepository = contactRepository;
    }

    public ContactResponseDTO createContact(Long id, ContactRequestDTO request) {
       Client searchedClient = clientRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found;"));

        Contact contact = new Contact();
        contact.setName(request.name());
        contact.setEmail(request.email());
        contact.setPhone(request.phone());
        contact.setRole(request.role());
        contact.setActive(true);
        contact.setClient(searchedClient);
        Contact savedContact = contactRepository.save(contact);

        return convertToDTO(savedContact);

    }

    public ContactResponseDTO convertToDTO(Contact contact) {
        return  new ContactResponseDTO(
                contact.getId(),
                contact.getName(),
                contact.getRole(),
                contact.getEmail(),
                contact.getPhone(),
                contact.isActive(),
                contact.getClient().getIdClient()
        );
    }

    public ContactResponseDTO findContactById(Long id) {
      Contact contactSearched =  contactRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found;"));
      return  convertToDTO(contactSearched);

    }

    public ContactResponseDTO updateContact(Long id, ContactRequestDTO request) {

        Contact contact = contactRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found;"));

        contact.setId(id);

        contact.setName(request.name());

        contact.setEmail(request.email());

        contact.setPhone(request.phone());

        contact.setRole(request.role());

        contact.setActive(true);

        Contact updatedContact = contactRepository.save(contact);

        return convertToDTO(updatedContact);

    }

    public ContactResponseDTO deactiveContact(Long id) {

        Contact contactSearched = contactRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contactSearched.setActive(false);

        return convertToDTO(contactSearched);


    }


}
