package com.example.shopapp.controllers;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.dto.OrderDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.repositories.ClientRepo;
import com.example.shopapp.repositories.OrderRepo;
import com.example.shopapp.services.ClientService;
import com.example.shopapp.wrappers.ClientWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class WebMvcClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;
    @MockBean
    private ClientRepo clientRepo;
    @MockBean
    private OrderRepo orderRepo;

    private static final ClientDTO CLIENT_DTO = new ClientDTO(5L, "Michael", "Jordan", "Warsaw",
            "Prosta", "50-500", "Poland");
    private static final ProductDTO PRODUCT_DTO1 = ProductDTO.builder()
            .productId(1L)
            .price(1.5)
            .build();
    private static final ProductDTO PRODUCT_DTO2 = ProductDTO.builder()
            .productId(2L)
            .price(3.0)
            .build();
    private static final ProductDTO PRODUCT_DTO3 = ProductDTO.builder()
            .productId(3L)
            .price(5.5)
            .build();

    @Test
    public void testClient() throws Exception {
        ClientWrapper clientWrapper = clientWrapperSetUp();
        OrderDTO orderDTO = orderDTOSetUp();
        given(clientService.addClientAndOrder(clientWrapper)).willReturn(orderDTO);

        mvc.perform(post("/shop/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(clientWrapper)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice", is(orderDTO.getTotalPrice())))
                .andExpect(jsonPath("$.products", hasSize(4)))
                .andExpect(jsonPath("$.clientDTO.clientId", is(orderDTO.getClientDTO().getClientId().intValue())));
        verify(clientService).addClientAndOrder(any(ClientWrapper.class));
    }

    private ClientWrapper clientWrapperSetUp() {
        var clientWrapper = new ClientWrapper();
        var theOrderProductIds = "2,1,3,3";
        clientWrapper.setOrderProductIds(theOrderProductIds);
        clientWrapper.setClient(CLIENT_DTO);

        return clientWrapper;
    }

    private OrderDTO orderDTOSetUp() {
        var productDTOList = List.of(PRODUCT_DTO2, PRODUCT_DTO1, PRODUCT_DTO3, PRODUCT_DTO3);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalPrice(15.5);
        orderDTO.setProducts(productDTOList);
        orderDTO.setClientDTO(CLIENT_DTO);
        return orderDTO;
    }
    @SneakyThrows(Exception.class)
    private static String asJsonString(final Object obj) {
        return new ObjectMapper().writeValueAsString(obj);
    }
}