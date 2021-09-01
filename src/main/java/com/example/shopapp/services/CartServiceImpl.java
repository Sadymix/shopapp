package com.example.shopapp.services;

import com.example.shopapp.dto.CartDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> addProductsToBasket(CartDTO theProductIds) {
        String s = theProductIds.getProductIds();

        String[] productIdsArr = s.split(",");
        var productIdsList = Arrays.stream(productIdsArr)
                .map(Long::parseLong)
                .distinct()
                .collect(Collectors.toList());
        var products = productRepo.findAllById(productIdsList);

        return Arrays.stream(productIdsArr)
                .map(Long::parseLong)
                .map(id -> getProductWithId(id, products))
                .map(productMapper::toDto)
                .toList();
    }

    private Product getProductWithId(Long id, Iterable<Product> iterableProduct) {

        Product theProduct = new Product();
        for(Product product: iterableProduct) {
            if(product.getProductId() != id) {
                continue;
            }else {
                theProduct = product;
            }
        }
        return theProduct;
    }
}
