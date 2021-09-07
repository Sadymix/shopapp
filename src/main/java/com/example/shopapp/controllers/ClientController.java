package com.example.shopapp.controllers;

import com.example.shopapp.services.ClientService;
import com.example.shopapp.wrappers.ClientWrapper;
import com.example.shopapp.wrappers.OrderWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PostMapping("/order")
    public String client(@ModelAttribute("clientWrapper") ClientWrapper clientWrapper, Model model) {
        var clientDTO = clientWrapper.getClient();

        clientService.addClientAndOrder(clientDTO, clientWrapper);

        var theOrderProducts = clientService.getProductsByIds(clientWrapper);

        model.addAttribute("orderProducts", theOrderProducts);

        model.addAttribute("client", clientDTO);

        model.addAttribute("order", new OrderWrapper());

        return "order";
    }
}
