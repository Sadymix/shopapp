package com.example.shopapp.services;

import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    private final ProductMapper productMapper;
    @Override
    public List<ProductDTO> getAllProducts() {

        List<Product> products = (List<Product>) productRepo.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }


}
