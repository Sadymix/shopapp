package com.example.shopapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class OrderDTO {

    private Long orderId;

    @Builder
    public OrderDTO(Long orderId) {
        this.orderId = orderId;
    }
}
