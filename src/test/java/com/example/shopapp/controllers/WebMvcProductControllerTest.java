package com.example.shopapp.controllers;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.repositories.ProductRepo;
import com.example.shopapp.services.CartService;
import com.example.shopapp.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class WebMvcProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private ProductRepo productRepo;
    @MockBean
    private CartService cartService;

    private static final ProductDTO PRODUCT_DTO1 = ProductDTO.builder()
            .productId(1L)
            .price(1.5)
            .build();
    private static final ProductDTO PRODUCT_DTO2 = ProductDTO.builder()
            .productId(2L)
            .price(3.0)
            .build();
    private static final ProductDTO PRODUCT_DTO3 = ProductDTO.builder()
            .productId(3L)
            .price(5.5)
            .build();

    @Test
    public void testGetProductsList() throws Exception{
        ProductDTO productDTO = new ProductDTO(2L, "milk", 5.0);
        var productDTOList = List.of(productDTO);

        given(productService.getAllProducts()).willReturn(productDTOList);

        mvc.perform(get("/shop/products"))
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId", is(productDTO.getProductId().intValue())))
                .andExpect(jsonPath("$[0].productName", is(productDTO.getProductName())))
                .andExpect(jsonPath("$[0].price", is(productDTO.getPrice())));
        verify(productService).getAllProducts();
    }

    @Test
    public void testGetCartProductList() throws Exception{
        var productIdsString = "2,1,3,2";
        var productDTOList = List.of(PRODUCT_DTO1, PRODUCT_DTO2, PRODUCT_DTO3);

        given(cartService.getProductsByIds(productIdsString)).willReturn(productDTOList);

        mvc.perform(post("/shop/cart")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .content(productIdsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].productId", is(Character.getNumericValue(productIdsString.charAt(2)))))
                .andExpect(jsonPath("$[1].productId", is(Character.getNumericValue(productIdsString.charAt(0)))))
                .andExpect(jsonPath("$[2].productId", is(Character.getNumericValue(productIdsString.charAt(4)))));

    }
}