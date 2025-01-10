package com.example.backend.controller;

import com.azure.core.http.HttpResponse;
import com.example.backend.model.MyUser;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;

    @GetMapping("/users/loggedIn")
    public ResponseEntity<MyUser> user() {
        return new ResponseEntity<>(userWebAuthenticationService.getLoggedInUser(),HttpStatus.OK);
    }
}
