package com.logingrupp5.logingrupp5.repository;

import org.springframework.data.repository.CrudRepository;

import com.logingrupp5.logingrupp5.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    
}
