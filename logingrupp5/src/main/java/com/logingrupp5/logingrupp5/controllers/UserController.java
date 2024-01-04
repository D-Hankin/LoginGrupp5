package com.logingrupp5.logingrupp5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.logingrupp5.logingrupp5.model.User;
import com.logingrupp5.logingrupp5.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }
    
    @PostMapping("/register/new-user")
    public String registerUser(@ModelAttribute("user") User user) {
        System.out.println("saved!!!!!!!!!!!!!!!!!!!!!!");
        userRepository.save(user);

        return "redirect:/";
    }
}
