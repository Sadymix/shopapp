package com.example.shopapp.mappers;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper{

    ProductDTO toDto(Product productEntity){
        return ProductDTO.builder()
                .productId(productEntity.getProductId())
                .price(productEntity.getPrice())
                .productName(productEntity.getProductName())
                .build();
    }
}