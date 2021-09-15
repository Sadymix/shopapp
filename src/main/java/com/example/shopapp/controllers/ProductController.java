package com.example.shopapp.controllers;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.services.CartService;
import com.example.shopapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/products")
    public List<ProductDTO> getProductsList() {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/cart", consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<ProductDTO> getCartProductsList(@RequestBody String productIds) {
        return cartService.getProductsByIds(productIds);
    }

}
