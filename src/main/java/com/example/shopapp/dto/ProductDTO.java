package com.example.shopapp.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class ProductDTO {


    private Long productId;
    @NotNull
    private double price;
    @NotNull
    private String productName;

    @Builder
    public ProductDTO(Long productId, @NotNull double price, @NotNull String productName) {
        this.productId = productId;
        this.price = price;
        this.productName = productName;
    }
}
