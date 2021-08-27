package com.example.shopapp.mappers;

import com.example.shopapp.models.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper tested = new ProductMapper();

    @Test
    void testToDto() {
        var product = new Product(1L, "test prod", 1.2);

        var productDto = tested.toDto(product);

        assertNotNull(productDto);
        assertEquals(product.getProductId(), productDto.getProductId());
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getPrice(), productDto.getPrice());
    }
}