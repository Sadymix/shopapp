package com.example.shopapp.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
@AllArgsConstructor
public class ProductDTO {


    private Long productId;
    @NotNull
    private double price;
    @NotNull
    private String productName;


}
