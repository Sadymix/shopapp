package com.example.shopapp.mappers;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.models.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toEntity(ClientDTO clientDTO) {
        return Client.builder()
                .clientId(clientDTO.getClientId())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .city(clientDTO.getCity())
                .street(clientDTO.getStreet())
                .postalCode(clientDTO.getPostalCode())
                .country(clientDTO.getCountry())
                .build();
    }

    public ClientDTO toDTO(Client client) {
        return ClientDTO.builder()
                .clientId(client.getClientId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .city(client.getCity())
                .street(client.getStreet())
                .postalCode(client.getPostalCode())
                .country(client.getCountry())
                .build();
    }
}
