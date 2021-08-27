package com.example.shopapp.mappers;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.models.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDTO toDTO(Client clientEntity) {
        return ClientDTO.builder()
                .clientId(clientEntity.getClientId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .city(clientEntity.getCity())
                .street(clientEntity.getStreet())
                .postalCode(clientEntity.getPostalCode())
                .country(clientEntity.getCountry())
                .build();
    }
}
