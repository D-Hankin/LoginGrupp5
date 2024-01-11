package com.logingrupp5.logingrupp5.controllers;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.logingrupp5.logingrupp5.model.UserOrder;
import com.logingrupp5.logingrupp5.repository.ProductRepository;
import com.logingrupp5.logingrupp5.repository.UserOrderRepository;
import com.logingrupp5.logingrupp5.repository.UserRepository;


@Controller
public class UserOrderController {

    @Autowired
    private final UserOrderRepository orderRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired 
    private final ProductRepository productRepository;

    public UserOrderController(UserOrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    
    @PostMapping("/addToCart/{productId}/{productName}")
    public String addItem(@PathVariable("productId") int productId, @PathVariable("productName") String productName, Model model, Authentication authentication) {
        System.out.println("item added: " + productName);
        model.addAttribute("authentication", authentication);
        if (authentication != null && authentication.isAuthenticated()) {

            if(orderRepository.checkProductQuantity(authentication.getName(), productName) > 0) {
                orderRepository.increaseProductQuantity(authentication.getName(), productName);
                //orderRepository.updateTotalPrice()
                System.out.println(orderRepository.checkProductQuantity(authentication.getName(), productName));

            } else {
                UserOrder userOrder = new UserOrder();
                userOrder.setUsername(authentication.getName());
                userOrder.setProductName(productName);
                userOrder.setQuantity(1);
                orderRepository.save(userOrder);
            }
        }

        return "redirect:/productPage/{productId}";
    }

    @GetMapping("/myOrders")
    public String myOrdersPage(Authentication authentication, Model model) {

        List<UserOrder> userOrders = orderRepository.findByUsername(authentication.getName());
        model.addAttribute("username", authentication.getName());
        model.addAttribute("userOrders", userOrders);

        BigDecimal grandTotal = new BigDecimal(0);
        for (UserOrder item : userOrders) {
            if (item.getProduct() != null && item.getProduct().getProductPrice() != null) {
                item.setTotalPrice(item.getProduct().getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                System.out.println(item.getTotalPrice());
                grandTotal = grandTotal.add(item.getTotalPrice());
            }
        }
        orderRepository.saveAll(userOrders);
        model.addAttribute("grandTotal", grandTotal);


        return "myOrders";
    }

    @PostMapping("/myOrders/empty-cart/{username}")
    public String emptyCart(@PathVariable("username") String username) {

        System.out.println(username);
        orderRepository.deleteAllByUsername(username);

        return "redirect:/myOrders";
    }
    
}
