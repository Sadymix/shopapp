package com.example.shopapp.services;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.mappers.ClientMapper;
import com.example.shopapp.models.Client;
import com.example.shopapp.repositories.ClientRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepo clientRepo;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService tested;

    @BeforeEach
    void setUp() {

//        Client
//
//        when(ClientRepo.save())
//
        when(clientMapper.toEntity(any(ClientDTO.class)))
                .thenReturn(Client.builder().build());
    }

    @Test
    void testAddClient() {
        ClientDTO clientDTO = new ClientDTO(1L, "Jan", "Kowalski", "Wrocław", "Sucha", "12-345", "Wrocław");
        tested.addClient(clientDTO);
        Assertions.assertEquals(clientDTO, clientRepo.findById(clientDTO.getClientId()));

    }

}