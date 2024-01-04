package com.logingrupp5.logingrupp5.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.logingrupp5.logingrupp5.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
