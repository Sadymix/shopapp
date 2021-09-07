package com.example.shopapp.services;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ClientMapper;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ClientRepo;
import com.example.shopapp.repositories.ProductRepo;
import com.example.shopapp.wrappers.ClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientMapper clientMapper;

    private final ClientRepo clientRepo;

    private final ProductRepo productRepo;

    private final ProductMapper productMapper;

    public void addClient(ClientDTO clientDTO) {

        var client = clientMapper.toEntity(clientDTO);
        clientRepo.save(client);
    }

    public List<ProductDTO> getProductsByIds(ClientWrapper clientWrapper) {
        var theProductIdsString = clientWrapper.getOrderProductIds();

        var arr = theProductIdsString.split(",");

        var productIdsList = Arrays.stream(arr)
                .map(Long::parseLong)
                .distinct()
                .toList();
        var products = productRepo.findAll();

        return Arrays.stream(arr)
                .map(Long::parseLong)
                .map(id -> getProductWithIds(id, products))
                .map(productMapper::toDto)
                .toList();
    }

    private Product getProductWithIds(Long id, Iterable<Product> iterableProduct) {

        for(Product product:iterableProduct) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

}
