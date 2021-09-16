package com.example.shopapp.mappers;

import com.example.shopapp.dto.OrderDTO;
import com.example.shopapp.models.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ProductMapper productMapper;
    private final ClientMapper clientMapper;

    public OrderDTO toDTO(Order order) {
        var productsList = order.getProductList();
        var productsDTOList = productsList.stream()
                .map(productMapper::toDto)
                .toList();
        var clientDTO = clientMapper.toDTO(order.getClient());
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .totalPrice(order.getTotalPrice())
                .products(productsDTOList)
                .clientDTO(clientDTO)
                .build();
    }
}
