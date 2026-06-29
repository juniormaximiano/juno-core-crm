package com.juno.corecrm.controller;

import com.juno.corecrm.DTO.Contact.ContactRequestDTO;
import com.juno.corecrm.DTO.Contact.ContactResponseDTO;
import com.juno.corecrm.service.ClientService;
import com.juno.corecrm.service.ContactService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ContactController {

    public final ClientService clientService;
    public final ContactService contactService;

    public ContactController(ClientService clientService, ContactService contactService) {
        this.clientService = clientService;
        this.contactService = contactService;
    }

    @PostMapping("/{id}/contacts")
    public ContactResponseDTO createContact(@PathVariable Long id, @RequestBody ContactRequestDTO contactRequestDTO) {
        return contactService.createContact(id, contactRequestDTO);
    }

    @GetMapping("/{id}/contacts")
    public ContactResponseDTO findContactById(@PathVariable Long id) {
        return contactService.findContactById(id);
    }

    @PutMapping("/{id}/contacts")
    public ContactResponseDTO updateContact(@PathVariable Long id, @RequestBody ContactRequestDTO contactRequestDTO) {
        return contactService.updateContact(id, contactRequestDTO);
    }
}
