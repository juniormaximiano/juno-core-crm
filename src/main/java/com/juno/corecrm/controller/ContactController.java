package com.juno.corecrm.controller;

import com.juno.corecrm.DTO.Contact.ContactRequestDTO;
import com.juno.corecrm.DTO.Contact.ContactResponseDTO;
import com.juno.corecrm.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/company/{companyId}")
    public ContactResponseDTO createContact(
            @PathVariable Long companyId,
            @RequestBody ContactRequestDTO request
    ) {
        return contactService.createContact(companyId, request);
    }

    @GetMapping
    public List<ContactResponseDTO> findAllContacts() {
        return contactService.listAllContacts();
    }

    @GetMapping("/{contactId}")
    public ContactResponseDTO findContactById(
            @PathVariable Long contactId
    ) {
        return contactService.findById(contactId);
    }

    @GetMapping("/company/{companyId}")
    public ContactResponseDTO findContactsBycompany(
            @PathVariable Long companyId
    ) {
        return contactService.findContactById(companyId);
    }

    @PutMapping("/{contactId}")
    public ContactResponseDTO updateContact(
            @PathVariable Long contactId,
            @RequestBody ContactRequestDTO request
    ) {
        return contactService.updateContact(contactId, request);
    }

    @PatchMapping("/{contactId}/deactivate")
    public ContactResponseDTO deactivateContact(
            @PathVariable Long contactId
    ) {
        return contactService.deactiveContact(contactId);
    }
}
