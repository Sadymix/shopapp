package com.example.shopapp.services;

import com.example.shopapp.dto.OrderDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ClientMapper;
import com.example.shopapp.mappers.OrderMapper;
import com.example.shopapp.repositories.ClientRepo;
import com.example.shopapp.repositories.OrderRepo;
import com.example.shopapp.wrappers.ClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepo clientRepo;
    private final OrderRepo orderRepo;
    private final CartService cartService;
    private final OrderMapper orderMapper;

    public OrderDTO addClientAndOrder(ClientWrapper clientWrapper) {
        var client = clientMapper.toEntity(clientWrapper.getClient());
        var orderClient = clientRepo.save(client);
        var orderProductsDTOList = cartService.getProductsByIds(clientWrapper.getOrderProductIds());


        var totalPrice = getOrderTotalPrice(orderProductsDTOList);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setClientDTO(clientMapper.toDTO(orderClient));
        orderDTO.setProducts(orderProductsDTOList);
        orderDTO.setTotalPrice(totalPrice);
        orderRepo.save(orderMapper.toEntity(orderDTO));

        return orderDTO;
    }

    private Double getOrderTotalPrice(List<ProductDTO> orderProductsList) {
        return orderProductsList.stream()
                .map(ProductDTO::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
