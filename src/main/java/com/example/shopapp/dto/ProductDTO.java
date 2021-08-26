package com.example.shopapp.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class ProductDTO {


    private Long productId;
    @NotNull
    private double price;
    @NotNull
    private String productName;

}
