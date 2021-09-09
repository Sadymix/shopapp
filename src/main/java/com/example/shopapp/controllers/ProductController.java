package com.example.shopapp.controllers;

import com.example.shopapp.dto.CartDTO;
import com.example.shopapp.services.CartService;
import com.example.shopapp.services.ProductService;
import com.example.shopapp.wrappers.ClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shop")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    private final CartService cartService;

    @GetMapping("/products")
    public String listProducts(Model model) {

        var theProducts = productService.getAllProducts();

        model.addAttribute("products", theProducts);

        model.addAttribute("formIdList", new CartDTO());

        return "product-list";
    }

    @PostMapping("/cart")
    public String addProductsToBasket(@ModelAttribute("formIdList") CartDTO theProductIds, Model model) {

        var theBasketProducts = cartService.getProductsByIds(theProductIds.getProductIds());

        model.addAttribute("basketProducts", theBasketProducts);

        model.addAttribute("clientWrapper", new ClientWrapper());

        return "basket-list";
    }
}

