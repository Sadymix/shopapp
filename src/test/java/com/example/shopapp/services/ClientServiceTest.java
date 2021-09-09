package com.example.shopapp.services;

import com.example.shopapp.dto.ClientDTO;
import com.example.shopapp.dto.ProductDTO;
import com.example.shopapp.mappers.ClientMapper;
import com.example.shopapp.mappers.ProductMapper;
import com.example.shopapp.models.Client;
import com.example.shopapp.models.Order;
import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ClientRepo;
import com.example.shopapp.repositories.OrderRepo;
import com.example.shopapp.repositories.ProductRepo;
import com.example.shopapp.wrappers.ClientWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepo clientRepo;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ProductRepo productRepo;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private ClientService tested;

    @BeforeEach
    void setUp() {
        var clientWrapper = clientWrapperSetUp();
        var theArrayOfOrderProductIds = clientWrapper.getOrderProductIds().split(",");
        var productIdsList = Arrays.stream(theArrayOfOrderProductIds)
                .map(Long::parseLong)
                .distinct()
                .toList();
        when(productMapper.toDto(any(Product.class))).thenAnswer(invocationOnMock -> {
            Product product = invocationOnMock.getArgument(0);
            return ProductDTO.builder().productId(product.getProductId()).price(product.getPrice()).build();
        });
        doReturn(List.of(mockProduct(1L, 1.5),
                mockProduct(2L, 3.0),
                mockProduct(3L, 5.5),
                mockProduct(4L, 4.5),
                mockProduct(5L, 5.0)))
                .when(productRepo).findAllById(productIdsList);
    }

    @Test
    void testGetOrderProductsByIds() {
        var clientWrapper = clientWrapperSetUp();
        var orderProductIdsList = orderProductsIdListSetUp(clientWrapper);

        var orderProductsDTO = tested.getOrderProductsByIds(clientWrapper);

        assertEquals(6, orderProductsDTO.size());
        verify(productRepo).findAllById(orderProductIdsList);
        verify(productMapper, times(6)).toDto(any(Product.class));
        assertEqualsIds(orderProductsDTO);

    }

    @Test
    void testAddClientAndOrder() {
        var clientWrapper = clientWrapperSetUp();
        var client = toEntity(clientWrapper.getClient());
        stubbingSetUp(client);
        Order order = orderSetUp(clientWrapper, client);

        verify(productMapper, times(6)).toEntity(any(ProductDTO.class));
        verify(clientRepo, atLeast(0)).save(client);
        verify(orderRepo, atLeast(0)).save(order);
        assertEqualsIds(order);
        assertEqualsPrices(order);
    }

    private Product mockProduct(Long id, Double price) {

        var product = mock(Product.class);
        doReturn(id).when(product).getProductId();
        doReturn(price).when(product).getPrice();
        return product;
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

    private void stubbingSetUp(Client client) {
        when(clientMapper.toEntity(any(ClientDTO.class)))
                .thenReturn(Client.builder().build());
        when(clientRepo.save(any(Client.class))).thenReturn(client);
        when(productMapper.toEntity(any(ProductDTO.class))).thenAnswer(invocationOnMock -> {
            ProductDTO productDTO = invocationOnMock.getArgument(0);
            return Product.builder().productId(productDTO.getProductId()).price(productDTO.getPrice()).build();
        });
    }

    private Order orderSetUp(ClientWrapper clientWrapper, Client client) {
        var productsDTOList = tested.getOrderProductsByIds(clientWrapper);
        var productsList = productsDTOList.stream()
                .map(productMapper::toEntity)
                .toList();
        var totalPrice = getTotalPrice(productsList);
        var order = new Order(1L, totalPrice, productsList, client);
        when((orderRepo).save(any(Order.class))).thenReturn(order);
        tested.addClientAndOrder(clientWrapper);

        return order;
    }

    private void assertEqualsIds(Order order) {
        assertEquals(1L, order.getProductList().get(0).getProductId());
        assertEquals(4L, order.getProductList().get(1).getProductId());
        assertEquals(3L, order.getProductList().get(2).getProductId());
        assertEquals(2L, order.getProductList().get(3).getProductId());
        assertEquals(5L, order.getProductList().get(4).getProductId());
        assertEquals(5L, order.getProductList().get(5).getProductId());
    }

    private void assertEqualsIds(List<ProductDTO> orderProductsDTOList) {
        assertEquals(1L, orderProductsDTOList.get(0).getProductId());
        assertEquals(4L, orderProductsDTOList.get(1).getProductId());
        assertEquals(3L, orderProductsDTOList.get(2).getProductId());
        assertEquals(2L, orderProductsDTOList.get(3).getProductId());
        assertEquals(5L, orderProductsDTOList.get(4).getProductId());
        assertEquals(5L, orderProductsDTOList.get(5).getProductId());
    }

    private void assertEqualsPrices(Order order) {
        assertEquals(1.5, order.getProductList().get(0).getPrice());
        assertEquals(4.5, order.getProductList().get(1).getPrice());
        assertEquals(5.5, order.getProductList().get(2).getPrice());
        assertEquals(3.0, order.getProductList().get(3).getPrice());
        assertEquals(5.0, order.getProductList().get(4).getPrice());
        assertEquals(5.0, order.getProductList().get(5).getPrice());
    }

    private List<Long> orderProductsIdListSetUp(ClientWrapper clientWrapper) {
        String[] orderProductIdsArr = clientWrapper.getOrderProductIds().split(",");
        var orderProductIdsList = Arrays.stream(orderProductIdsArr)
                .map(Long::parseLong)
                .distinct()
                .toList();
        return orderProductIdsList;
    }

    private Double getTotalPrice(List<Product> productList) {
        var totalPrice = 0.0;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    private Client toEntity(ClientDTO clientDTO) {
        return Client.builder()
                .clientId(clientDTO.getClientId())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .city(clientDTO.getCity())
                .street(clientDTO.getStreet())
                .postalCode(clientDTO.getPostalCode())
                .country(clientDTO.getCountry())
                .build();
    }
}