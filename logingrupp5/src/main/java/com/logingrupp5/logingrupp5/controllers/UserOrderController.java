package com.logingrupp5.logingrupp5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logingrupp5.logingrupp5.model.UserOrder;
import com.logingrupp5.logingrupp5.repository.UserOrderRepository;
import com.logingrupp5.logingrupp5.repository.UserRepository;

@Controller
public class UserOrderController {

    @Autowired
    private final UserOrderRepository orderRepository;

    @Autowired
    private final UserRepository userRepository;

    public UserOrderController(UserOrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
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

    @GetMapping("/myOrders")
    public String myOrdersPage(Authentication authentication, Model model) {

        model.addAttribute("userRepository", userRepository);
        model.addAttribute("orderRepository", orderRepository);
        model.addAttribute("username", authentication.getName());
        return "myOrders";
    }
}
