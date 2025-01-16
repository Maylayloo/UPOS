package com.example.backend.controller;

import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.model.MyUser;
import com.example.backend.service.authenticationService.AuthenticationService;
import com.example.backend.service.userService.UserDataManagementService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@SessionAttributes("user")
public class LoginController {
    @Autowired
    private UserDataManagementService userDataManagementService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest, HttpServletRequest request) {
        try {
            authenticationService.executeLoginRequest(loginRequest,request);
            MyUser user =userDataManagementService.getUserByEmail(loginRequest.getUsername());
            Map<String , String> resultRole = new HashMap<>();
            resultRole.put("role", user.getRole().toString());
            return new ResponseEntity<>(resultRole, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

}
