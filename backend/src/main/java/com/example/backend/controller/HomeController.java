package com.example.backend.controller;

import com.example.backend.model.MyUser;
import com.example.backend.service.userService.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/")
    public String home() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=  (User)authentication.getPrincipal();
        String email= user.getUsername();
        String password = user.getPassword();
        return email+" "+password;



}
}
