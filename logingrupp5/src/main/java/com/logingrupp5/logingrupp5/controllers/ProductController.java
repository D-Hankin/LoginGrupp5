package com.logingrupp5.logingrupp5.controllers;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.logingrupp5.logingrupp5.model.Product;
import com.logingrupp5.logingrupp5.repository.ProductRepository;

@Controller
public class ProductController {
    
    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @GetMapping("/productPage/{productId}")
        public String getProductInfo(@PathVariable int productId, Model model, Authentication authentication) {
            Product product = productRepository.findById(productId)
            .orElseThrow(() -> new NoSuchElementException("No product found by ID" + productId));
            model.addAttribute("product", product);
            model.addAttribute("authentication", authentication!= null && authentication.isAuthenticated());
            return "productPage";
        }
    }