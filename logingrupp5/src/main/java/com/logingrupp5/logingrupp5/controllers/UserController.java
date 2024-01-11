package com.logingrupp5.logingrupp5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.logingrupp5.logingrupp5.model.User;
import com.logingrupp5.logingrupp5.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    int error = 0;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerUser(Model model) {

        if (error == 1) {
            model.addAttribute("error", "OOPS! An error has occurred. Go back and try again!");
            error = 0;
        }

        return "/register";
    }

    @PostMapping("/register/new-user")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email, Model model) throws InterruptedException {

        if (username.trim().isEmpty() || password.trim().isEmpty() || firstName.trim().isEmpty() || lastName.trim().isEmpty() || email.trim().isEmpty()) {
            
            model.addAttribute("error", "OOPS! You need to fill in all the fields. Please try again!");
            
            return "register";
            
        } else {
            try {
                User user = new User();
                String encryptedPassword = bcryptEncoder.encode(password);
                user.setUsername(username);
                user.setPassword(encryptedPassword);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                userRepository.save(user);

                return "redirect:/";

            } catch (Exception e) {
                
                error = 1;

                return "redirect:/register";
            }
        }
    }
    
    @GetMapping("/error")
    public String errorPage(Model model) {
         
        model.addAttribute("error", "OOPS! An error has occurred. Go back and try again!");

        return "redirect:/register";
    }

    @GetMapping("/user/{username}")
    public String userIndex(@PathVariable String username, Model model) {
        
        return "redirect:/";
    }
    
}
