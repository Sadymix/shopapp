package com.example.shopapp.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {


    private Long productId;
    @NotNull
    private String productName;
    @NotNull
    private double price;
}
