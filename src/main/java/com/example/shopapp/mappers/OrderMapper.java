package com.example.shopapp.mappers;

import com.example.shopapp.dto.OrderDTO;
import com.example.shopapp.models.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order orderEntity) {
        return OrderDTO.builder()
                .orderId(orderEntity.getOrderId())
                .build();
    }
}
