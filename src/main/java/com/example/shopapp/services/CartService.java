package com.example.shopapp.services;

import com.example.shopapp.dto.CartDTO;
import com.example.shopapp.dto.ProductDTO;

import java.util.List;

public interface CartService {
    public List<ProductDTO> addProductsToBasket(CartDTO theProductIds);

}
