package com.logingrupp5.logingrupp5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logingrupp5.logingrupp5.repository.OrderRepository;

@Controller
public class OrderController {

    private String username;
    
    @PostMapping("/addToCart/{productId}/{productName}")
    public String addItem(@PathVariable("productId") int productId, @PathVariable("productName") String productName, Model model, Authentication authentication) {
        System.out.println("item added: " + productName);
        model.addAttribute("authentication", authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();

            System.out.println(username);
        }

        return "redirect:/productPage/{productId}";
    }
}
