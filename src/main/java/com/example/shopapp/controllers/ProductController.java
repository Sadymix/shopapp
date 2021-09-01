package com.example.shopapp.controllers;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.services.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/shop")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;


    @GetMapping("/products")
    public String listProducts(Model model) {

        List<ProductDTO> theProducts = productService.getAllProducts();

        model.addAttribute("products", theProducts);

        model.addAttribute("formIdList", new CartDto());

        return "product-list";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String addProductsToBasket(@ModelAttribute("formIdList")CartDto theProductIds, Model model) {
        List<ProductDTO> theProducts = productService.getAllProducts();

        List<ProductDTO> theBasketProducts = new ArrayList<>();

        String s = theProductIds.getProductIds();

        String[] productIdsArr = s.split(",");
        int size = productIdsArr.length;
        List<Integer> productIdsList = new ArrayList<>();

        for(int i = 0; i< productIdsArr.length; i++) {
            productIdsList.add(Integer.parseInt(productIdsArr[i]));
        }

        for(int i =0; i<productIdsList.size(); i++) {
            theBasketProducts.add(theProducts.get(productIdsList.get(i) - 1));
        }

        model.addAttribute("basketProducts" , theBasketProducts);

        return "basket-list";
    }

@Data
private class CartDto {
        private String productIds;
}

}

