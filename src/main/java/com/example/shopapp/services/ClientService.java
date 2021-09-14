package com.example.shopapp.services;

import com.example.shopapp.dto.OrderDTO;
import com.example.shopapp.mappers.ClientMapper;
import com.example.shopapp.mappers.OrderMapper;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ClientRepo;
import com.example.shopapp.repositories.OrderRepo;
import com.example.shopapp.wrappers.ClientWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepo clientRepo;
    private final ProductMapper productMapper;
    private final OrderRepo orderRepo;
    private final CartService cartService;
    private final OrderMapper orderMapper;

    public OrderDTO addClientAndOrder(ClientWrapper clientWrapper) {
        var client = clientMapper.toEntity(clientWrapper.getClient());
        var orderClient = clientRepo.save(client);
        var orderProductsDTOList = cartService.getProductsByIds(clientWrapper.getOrderProductIds());
        var orderProductsList = orderProductsDTOList.stream()
                .map(productMapper::toEntity)
                .toList();

        var totalPrice = getOrderTotalPrice(orderProductsList);
        Order order = new Order();
        order.setClient(orderClient);
        order.setProductList(orderProductsList);
        order.setTotalPrice(totalPrice);
        orderRepo.save(order);

        return orderMapper.toDTO(order);
    }

    private Double getOrderTotalPrice(List<Product> orderProductsList) {
        return orderProductsList.stream()
                .map(Product::getPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
