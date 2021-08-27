package com.example.shopapp.repositories;

import com.example.shopapp.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

}
