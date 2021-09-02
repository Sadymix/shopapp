package com.example.shopapp.services;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepo repo;
    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService tested;

    @BeforeEach
    void setUp() {
        when(repo.findAll())
                .thenReturn(List.of(new Product(), new Product(), new Product()));

        when(mapper.toDto(any(Product.class)))
                .thenReturn(ProductDTO.builder().build());
    }

    @Test
    void testGetProducts() {
        var products = tested.getAllProducts();

        Assertions.assertEquals(3, products.size());
        verify(repo).findAll();
        verify(mapper, times(3)).toDto(any(Product.class));
    }
}