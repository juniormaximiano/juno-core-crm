package com.juno.corecrm.service;

import com.juno.corecrm.domain.entity.Client;
import com.juno.corecrm.DTO.Client.ClientRequestDTO;
import com.juno.corecrm.DTO.Client.ClientResponseDTO;
import com.juno.corecrm.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO convertToDTO(Client client) {

        return new ClientResponseDTO(
                client.getIdClient(),
                client.getClientName(),
                client.getCompanyName(),
                client.getEmail(),
                client.getPhone(),
                client.isActive(),
                client.getCreatedAt()

        );
    }

    public ClientResponseDTO createClient(ClientRequestDTO DTO) {

        Client newclient = new Client();

        newclient.setClientName(DTO.clientName());
        newclient.setCompanyName(DTO.companyName());
        newclient.setEmail(DTO.email());
        newclient.setPhone(DTO.phone());
        newclient.setActive(true);
        newclient.setCreatedAt(LocalDate.now());

        var newClientCreated = clientRepository.save(newclient);

        return convertToDTO(newClientCreated);

    }

    public List<ClientResponseDTO> getAllClients() {

        var allClients = clientRepository.findAll();

        List<ClientResponseDTO> clientResponseDTOS = new ArrayList<>();

        for (var client : allClients) {
            clientResponseDTOS.add(convertToDTO(client));
        }

        return clientResponseDTOS;

    }

    public ClientResponseDTO findClientById(Long id) {

        Client searchedClient = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        return convertToDTO(searchedClient);
    }

    public ClientResponseDTO updateClient(Long id, ClientRequestDTO request) {

        Client searchedClient = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        searchedClient.setClientName(request.clientName());
        searchedClient.setCompanyName(request.companyName());
        searchedClient.setEmail(request.email());
        searchedClient.setPhone(request.phone());

        var ClientUpdated = clientRepository.save(searchedClient);

        return convertToDTO(ClientUpdated);

    }

    public ClientResponseDTO deactivateClient(Long id) {
        Client updatedClient = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        updatedClient.setActive(false);
        var ClientUpdated = clientRepository.save(updatedClient);
        return convertToDTO(ClientUpdated);
    }

    public List<ClientResponseDTO> findClientByName(String name) {

        var searchedClient = clientRepository.findByClientNameIsContainingIgnoreCase(name);

        List<ClientResponseDTO> clientResponseDTOS = new ArrayList<>();

        for (var client : searchedClient) {

            clientResponseDTOS.add(convertToDTO(client));
        }

        return clientResponseDTOS;

    }

    public List<ClientResponseDTO> findClientByCompanyName(String companyName) {

        var searchedClient = clientRepository.findByCompanyNameIsContainingIgnoreCase(companyName);

        List<ClientResponseDTO> clientResponseDTOS = new ArrayList<>();

        for (var client : searchedClient) {

            clientResponseDTOS.add(convertToDTO(client));
        }

        return clientResponseDTOS;
    }

    public List<ClientResponseDTO> findActiveClients() {

        var searchedClients = clientRepository.findAllByActiveTrue();

        List<ClientResponseDTO> clientResponseDTOS = new ArrayList<>();

        for (var client : searchedClients) {
            clientResponseDTOS.add(convertToDTO(client));
        }

        return clientResponseDTOS;

    }



}
