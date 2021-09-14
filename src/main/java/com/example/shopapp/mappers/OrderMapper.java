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

    public Order toEntity(OrderDTO orderDTO) {
        var productsDTOList = orderDTO.getProducts();
        var productsList = productsDTOList.stream()
                .map(productMapper::toEntity)
                .toList();
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .productList(productsList)
                .totalPrice(orderDTO.getTotalPrice())
                .client(clientMapper.toEntity(orderDTO.getClientDTO()))
                .build();
    }
}
