package com.logingrupp5.logingrupp5.controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.logingrupp5.logingrupp5.model.Product;
import com.logingrupp5.logingrupp5.repository.ProductRepository;

@Controller
public class IndexController {

    @Autowired
    private ProductRepository productRepository;

    Product prod = new Product();
    
    @GetMapping("/")
    public String index(Model model) {

         model.addAttribute("products", productRepository.findAll());
        

        
       
        
        return "index";
    }

    
}
