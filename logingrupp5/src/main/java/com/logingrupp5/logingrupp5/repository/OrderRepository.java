package com.logingrupp5.logingrupp5.repository;

import org.springframework.data.repository.CrudRepository;

import com.logingrupp5.logingrupp5.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    //Optional<Order> findByOrderName(String orderName);  
} 