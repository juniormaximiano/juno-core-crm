package com.juno.corecrm.controller;

import com.juno.corecrm.dto.ClientRequestDTO;
import com.juno.corecrm.dto.ClientResponseDTO;
import com.juno.corecrm.repository.ClientRepository;
import com.juno.corecrm.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;
    ClientRepository clientRepository;

    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
    }


    @PostMapping("/create")
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        var createdClient = clientService.createClient(clientRequestDTO);
        return createdClient;

    }

    @GetMapping("listAll")
    public List<ClientResponseDTO> getAllClients() {
        var searchedClients = clientService.getAllClients();
        return searchedClients;
    }
}
