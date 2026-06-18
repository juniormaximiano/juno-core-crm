package com.juno.corecrm.service;

import com.juno.corecrm.domain.entity.Client;
import com.juno.corecrm.dto.ClientRequestDTO;
import com.juno.corecrm.dto.ClientResponseDTO;
import com.juno.corecrm.repository.ClientRepository;
import org.springframework.stereotype.Service;

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

}
