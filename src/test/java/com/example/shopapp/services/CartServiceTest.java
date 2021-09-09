package com.example.shopapp.services;

import com.example.shopapp.dto.CartDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private ProductRepo productRepo;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private CartService tested;


    @BeforeEach
    void setUp() {
        var cartDTO = new CartDTO();
        cartDTO.setProductIds("1,5,3,2,5,5,2,4");
        String[] productIdsArr = cartDTO.getProductIds().split(",");


        var productIdsList = Arrays.stream(productIdsArr)
                .map(Long::parseLong)
                .distinct()
                .toList();

        doReturn(List.of(mockProduct(1L),
                mockProduct(2L),
                mockProduct(3L),
                mockProduct(4L),
                mockProduct(5L)))
                .when(productRepo).findAllById(productIdsList);

        when(productMapper.toDto(any(Product.class))).thenAnswer(invocationOnMock -> {
            Product product = invocationOnMock.getArgument(0);
            return ProductDTO.builder().productId(product.getProductId()).build();
        });
    }

    @Test
    void testGetProductsByIds() {

        var cartDTO = new CartDTO();
        cartDTO.setProductIds("1,5,3,2,5,5,2,4");
        String[] productIdsArr = cartDTO.getProductIds().split(",");
        var productIdsList = Arrays.stream(productIdsArr)
                .map(Long::parseLong)
                .distinct()
                .toList();

        var products = tested.getProductsByIds(cartDTO);
        assertEquals(8, products.size());
        verify(productRepo).findAllById(productIdsList);
        verify(productMapper, times(8)).toDto(any(Product.class));
        assertEquals(1L, products.get(0).getProductId());
        assertEquals(5L, products.get(1).getProductId());
        assertEquals(3L, products.get(2).getProductId());
        assertEquals(2L, products.get(3).getProductId());
        assertEquals(5L, products.get(4).getProductId());
        assertEquals(5L, products.get(5).getProductId());
        assertEquals(2L, products.get(6).getProductId());
        assertEquals(4L, products.get(7).getProductId());

    }

    private Product mockProduct(Long id) {

        var product = mock(Product.class);
        doReturn(id).when(product).getProductId();
        return product;

    }
}