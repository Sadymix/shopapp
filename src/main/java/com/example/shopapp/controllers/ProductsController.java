package com.example.shopapp.controllers;

import com.example.shopapp.models.Product;
import com.example.shopapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/shop")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model) {

        List<Product> theProducts = productService.getProducts();

        model.addAttribute("products", theProducts);

        return "product-list";
    }
}
