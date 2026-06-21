package com.juno.corecrm.controller;

import com.juno.corecrm.dto.ClientRequestDTO;
import com.juno.corecrm.dto.ClientResponseDTO;
import com.juno.corecrm.repository.ClientRepository;
import com.juno.corecrm.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
    }


    @PostMapping("/create")
    public ClientResponseDTO createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    @GetMapping("/listAll")
    public List<ClientResponseDTO> getAllClients() {
        var searchedClients = clientService.getAllClients();
        return searchedClients;
    }

    @GetMapping("/findById")
    public ClientResponseDTO getClientById(
            @RequestParam Long id
    ) {
        var searchedClient = clientService.findClientById(id);
        return searchedClient;
    }

    @PutMapping("/{id}")
    public ClientResponseDTO updateClient(
            @PathVariable Long id,
            @RequestBody ClientRequestDTO clientRequestDTO
    ) {
        return clientService.updateClient(id, clientRequestDTO);
    }

    @PatchMapping("/deactivate")
    public ClientResponseDTO deactivateClient(
            @RequestParam Long id
    ) {
        return clientService.deactivateClient(id);
    }

    @GetMapping("/getAllByName")
    public List<ClientResponseDTO> getAllClientsByName(@RequestParam String name) {
        return clientService.findClientByName(name);
    }

    @GetMapping("/findByCompanyName")
    public List<ClientResponseDTO> findByCompanyName(@RequestParam String CompanyName) {
        return clientService.findClientByCompanyName(CompanyName);
    }


}
