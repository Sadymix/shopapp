package com.example.shopapp.services;

import com.example.shopapp.models.Product;
import com.example.shopapp.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{


    private ProductRepo productRepo;

    @Override
    public List<Product> getProducts() {
      return (List<Product>) productRepo.findAll();
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        Query<Product> query = currentSession.createQuery("SELECT FROM Products", Product.class);
//
//        List<Product> products = query.getResultList();
//
//        return products;


    }

    @Override
    public Product getProduct() {
        return null;
    }
}
