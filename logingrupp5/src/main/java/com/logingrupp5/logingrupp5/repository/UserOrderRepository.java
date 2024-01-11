package com.logingrupp5.logingrupp5.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.logingrupp5.logingrupp5.model.UserOrder;

import jakarta.transaction.Transactional;

public interface UserOrderRepository extends CrudRepository<UserOrder, Integer>{
    List<UserOrder> findByUsername(String user);

    @Query("SELECT COUNT(u) FROM UserOrder u WHERE u.username = ?1 AND u.productName = ?2")
    int checkProductQuantity(String name, String productName);

    @Transactional
    @Modifying
    @Query("UPDATE UserOrder u SET u.quantity = u.quantity + 1 WHERE u.username = ?1 AND u.productName = ?2")
    void increaseProductQuantity(String name, String productName);

    @Transactional
    @Modifying
    @Query("UPDATE UserOrder u SET u.quantity = u.quantity - 1 WHERE u.username = ?1 AND u.productName = ?2")
    void decreaseProductQuantity(String name, String productName);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserOrder u WHERE u.username = ?1")
    void deleteAllByUsername(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserOrder u WHERE u.quantity = 0")
    void checkForZero();

    

} 