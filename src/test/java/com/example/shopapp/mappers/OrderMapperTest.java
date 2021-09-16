package com.example.shopapp.mappers;
import com.example.shopapp.models.Client;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
class OrderMapperTest {
    private final ProductMapper productMapper = new ProductMapper();
    private final ClientMapper clientMapper = new ClientMapper();
    private final OrderMapper tested = new OrderMapper(productMapper, clientMapper);

    private static final Product PRODUCT1 = Product.builder()
            .productId(1L)
            .price(1.5)
            .build();
    private static final Product PRODUCT2 = Product.builder()
            .productId(2L)
            .price(3.0)
            .build();
    private static final Product PRODUCT3 = Product.builder()
            .productId(3L)
            .price(5.5)
            .build();
    private static final Client CLIENT = new Client(5L, "Michael", "Jordan", "Warsaw",
            "Prosta", "50-500", "Poland", null);


    @Test
    void testToDTO() {
        var productList = List.of(PRODUCT1, PRODUCT2, PRODUCT3);
        var order = new Order(12L, 32.5, productList, CLIENT);

        var orderDTO = tested.toDTO(order);

        assertNotNull(orderDTO);
        assertEquals(order.getOrderId(), orderDTO.getOrderId());
        assertEquals(order.getTotalPrice(), orderDTO.getTotalPrice());
    }

}