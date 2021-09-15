package com.example.shopapp.services;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ClientMapper;
import com.example.shopapp.mappers.OrderMapper;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Client;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ClientRepo;
import com.example.shopapp.repositories.OrderRepo;
import com.example.shopapp.wrappers.ClientWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    private static final Client CLIENT = new Client(null, "Michael", "Jordan", "Warsaw",
            "Prosta", "50-500", "Poland", null);

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

    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientRepo clientRepo;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private OrderRepo orderRepo;
    @Mock
    private CartService cartService;
    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private ClientService tested;

    @BeforeEach
    void setUp() {
        var clientWrapper = clientWrapperSetUp();
        when(clientMapper.toEntity(any(ClientDTO.class))).thenReturn(CLIENT);
        when(clientRepo.save(CLIENT)).thenAnswer(invocationOnMock -> {
            Client c = invocationOnMock.getArgument(0);
            c.setClientId(1L);
            return c;
        });
        when(cartService.getProductsByIds(clientWrapper.getOrderProductIds()))
                .thenReturn(List.of(new ProductDTO(), new ProductDTO(), new ProductDTO()));
        when(productMapper.toEntity(any(ProductDTO.class)))
                .thenReturn(PRODUCT1, PRODUCT2, PRODUCT3);
    }

    @Test
    void testAddClientAndOrder() {
        var clientWrapper = clientWrapperSetUp();

        tested.addClientAndOrder(clientWrapper);
        verify(clientMapper).toEntity(any(ClientDTO.class));
        verify(clientRepo).save(CLIENT);
        verify(productMapper, times(3)).toEntity(any(ProductDTO.class));
        verify(orderMapper).toDTO(any(Order.class));
        verify(orderRepo).save(any(Order.class));
    }

    private ClientWrapper clientWrapperSetUp() {
        var clientWrapper = new ClientWrapper();
        var clientDTO = new ClientDTO(1L, "Michael", "Jordan", "Warsaw",
                "Prosta", "50-500", "Poland");
        var theOrderProductIds = "1,4,3,2,5,5";
        clientWrapper.setOrderProductIds(theOrderProductIds);
        clientWrapper.setClient(clientDTO);

        return clientWrapper;
    }
}