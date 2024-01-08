package com.logingrupp5.logingrupp5.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.logingrupp5.logingrupp5.model.Product;



public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByProductName(String productName);
}
