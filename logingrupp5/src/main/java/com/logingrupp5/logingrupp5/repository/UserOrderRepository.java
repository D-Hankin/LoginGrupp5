package com.logingrupp5.logingrupp5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.logingrupp5.logingrupp5.model.UserOrder;

import jakarta.transaction.Transactional;

public interface UserOrderRepository extends CrudRepository<UserOrder, Integer>{
    Optional<UserOrder> findByUsername(String username);

    @Query("SELECT COUNT(u) FROM UserOrder u WHERE u.username = ?1 AND u.productName = ?2")
    int checkProductQuantity(String name, String productName);

    @Transactional
    @Modifying
    @Query("UPDATE UserOrder u SET u.quantity = u.quantity + 1 WHERE u.username = ?1 AND u.productName = ?2")
    void increaseProductQuantity(String name, String productName);  
} 