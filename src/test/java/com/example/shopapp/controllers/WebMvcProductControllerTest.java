package com.example.shopapp.controllers;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.services.CartService;
import com.example.shopapp.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebMvcProductControllerTest {

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

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ProductService productService;
    @MockBean
    private CartService cartService;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser
    @Test
    public void testGetProductsList() throws Exception {

        ProductDTO productDTO = new ProductDTO(2L, "milk", 5.0);
        var productDTOList = List.of(productDTO);

        given(productService.getAllProducts()).willReturn(productDTOList);

        mvc.perform(get("/shop/products").accept(APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId", is(productDTO.getProductId().intValue())))
                .andExpect(jsonPath("$[0].productName", is(productDTO.getProductName())))
                .andExpect(jsonPath("$[0].price", is(productDTO.getPrice())));
        verify(productService).getAllProducts();
    }

    @WithMockUser
    @Test
    public void testGetCartProductList() throws Exception {
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

    @Test
    public void testGetProductListUnauthenticated() throws Exception {
        ProductDTO productDTO = new ProductDTO(2L, "milk", 5.0);
        var productDTOList = List.of(productDTO);

        given(productService.getAllProducts()).willReturn(productDTOList);

        mvc.perform(get("/shop/products"))
                .andExpect(status().isUnauthorized());
    }
}