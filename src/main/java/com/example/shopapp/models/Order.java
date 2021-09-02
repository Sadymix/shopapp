package com.example.shopapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "total_price")
    private double totalPrice;


    @ManyToMany
    @JoinTable(name = "product_order",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private List<Product> productList = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Column(name = "order_status")
    @Enumerated
    private OrderStatus orderStatus;


}
