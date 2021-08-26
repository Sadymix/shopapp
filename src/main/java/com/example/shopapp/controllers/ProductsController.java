package com.example.shopapp.controllers;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/shop")
@RequiredArgsConstructor
public class ProductsController {


    private final ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model) {

        List<ProductDTO> theProducts = productService.getProducts();

        model.addAttribute("products", theProducts);

        return "product-list";
    }
}

