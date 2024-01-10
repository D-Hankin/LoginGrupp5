package com.logingrupp5.logingrupp5.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.logingrupp5.logingrupp5.model.UserOrder;

public interface UserOrderRepository extends CrudRepository<UserOrder, Integer>{
    Optional<UserOrder> findByUsername(String username);  
} 