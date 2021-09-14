package com.example.shopapp.controllers;

import com.example.shopapp.dto.OrderDTO;
import com.example.shopapp.services.ClientService;
import com.example.shopapp.wrappers.ClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ClientController {

    private final ClientService clientService;


    @PostMapping(value = "/order",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO client(@RequestBody ClientWrapper clientWrapper) {
        return clientService.addClientAndOrder(clientWrapper);
    }
}
