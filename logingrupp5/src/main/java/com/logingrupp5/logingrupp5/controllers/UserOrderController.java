package com.logingrupp5.logingrupp5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logingrupp5.logingrupp5.model.UserOrder;
import com.logingrupp5.logingrupp5.repository.UserOrderRepository;

@Controller
public class UserOrderController {

    @Autowired
    private final UserOrderRepository orderRepository;

    public UserOrderController(UserOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @PostMapping("/addToCart/{productId}/{productName}")
    public String addItem(@PathVariable("productId") int productId, @PathVariable("productName") String productName, Model model, Authentication authentication) {
        System.out.println("item added: " + productName);
        model.addAttribute("authentication", authentication);
        if (authentication != null && authentication.isAuthenticated()) {

            UserOrder userOrder = new UserOrder();
            userOrder.setUsername(authentication.getName());
            userOrder.setProductName(productName);
            orderRepository.save(userOrder);
        }

        return "redirect:/productPage/{productId}";
    }
}
