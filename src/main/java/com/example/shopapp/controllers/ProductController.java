package com.example.shopapp.controllers;

import com.example.shopapp.dto.CartDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.services.CartService;
import com.example.shopapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/shop")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    private final CartService cartService;

    @GetMapping("/products")
    public String listProducts(Model model) {

        List<ProductDTO> theProducts = productService.getAllProducts();

        model.addAttribute("products", theProducts);

        model.addAttribute("formIdList", new CartDTO());

        return "product-list";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String addProductsToBasket(@ModelAttribute("formIdList") CartDTO theProductIds, Model model) {

        List<ProductDTO> theBasketProducts = cartService.getProductsByIds(theProductIds);

        model.addAttribute("basketProducts", theBasketProducts);

        return "basket-list";
    }
}

