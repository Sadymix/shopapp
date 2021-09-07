package com.example.shopapp.controllers;

import com.example.shopapp.services.CartService;
import com.example.shopapp.services.ClientService;
import com.example.shopapp.wrappers.ClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final CartService cartService;

    @PostMapping("/order")
    public String order(@ModelAttribute("clientWrapper") ClientWrapper clientWrapper, Model model) {
        var clientDTO = clientWrapper.getClient();

        clientService.addClient(clientDTO);

        var theOrderProducts = clientService.getProductsByIds(clientWrapper);

        model.addAttribute("orderProducts", theOrderProducts);

        model.addAttribute("client", clientDTO);

        return "order";
    }
}
