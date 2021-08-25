package com.example.shopapp.services;

import com.example.shopapp.models.Product;

import java.util.List;


public interface ProductService {



    public List<Product> getProducts();

    public Product getProduct();

}
