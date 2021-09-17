package com.example.shopapp.mappers;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.models.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientMapperTest {

    private final ClientMapper tested = new ClientMapper();

    @Test
    void testToEntity() {
        var clientDTO = new ClientDTO(5L, "Frank", "Deaf", "New York", "14th Street",
                "10001", "USA");
        var client = tested.toEntity(clientDTO);

        assertNotNull(client);
        assertEquals(clientDTO.getClientId(), client.getClientId());
        assertEquals(clientDTO.getFirstName(), client.getFirstName());
        assertEquals(clientDTO.getLastName(), client.getLastName());
        assertEquals(clientDTO.getCity(), client.getCity());
        assertEquals(clientDTO.getStreet(), client.getStreet());
        assertEquals(clientDTO.getPostalCode(), client.getPostalCode());
        assertEquals(clientDTO.getCountry(), client.getCountry());
    }

    @Test
    void testToDTO() {
        var client = new Client(1L, "Frank", "Deaf", "New York", "14th Street",
                "10001", "USA", null);

        var clientDTO = tested.toDTO(client);

        assertNotNull(clientDTO);
        assertEquals(client.getClientId(), clientDTO.getClientId());
        assertEquals(client.getFirstName(), clientDTO.getFirstName());
        assertEquals(client.getLastName(), clientDTO.getLastName());
        assertEquals(client.getCity(), clientDTO.getCity());
        assertEquals(client.getStreet(), clientDTO.getStreet());
        assertEquals(client.getPostalCode(), clientDTO.getPostalCode());
        assertEquals(client.getCountry(), clientDTO.getCountry());

    }
}