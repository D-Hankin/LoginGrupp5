package com.logingrupp5.logingrupp5.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.logingrupp5.logingrupp5.model.Product;
import com.logingrupp5.logingrupp5.model.UserOrder;
import com.logingrupp5.logingrupp5.repository.ProductRepository;

@Controller
public class IndexController {

    @Autowired
    private ProductRepository productRepository;

    Product prod = new Product();
    
    @GetMapping("/")
    public String index(Authentication authentication, Model model) {

         model.addAttribute("products", productRepository.findAll());

          if (authentication != null && authentication.isAuthenticated()) {

            model.addAttribute("username", authentication.getName());
            
            
        } 

         
        return "index";
    }

    
}
