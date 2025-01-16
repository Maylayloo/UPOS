package com.example.backend.controller;

import com.azure.core.http.HttpResponse;
import com.example.backend.dto.MyUserDTO;
import com.example.backend.mapper.MyUserMapper;
import com.example.backend.model.MyUser;
import com.example.backend.service.userService.UserDataManagementService;
import com.example.backend.service.userService.UserModificationService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;
    @Autowired
    private UserDataManagementService userDataManagementService;
    @Autowired
    private UserModificationService userModificationService;

    @GetMapping("/users/loggedIn")
    public ResponseEntity<MyUser> user() {
        return new ResponseEntity<>(userWebAuthenticationService.getLoggedInUser(),HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Optional<List<MyUser>>> users() {
        if (userWebAuthenticationService.getLoggedInUser() == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(userDataManagementService.getAllUsers(),HttpStatus.OK) ;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<MyUser>> user(@PathVariable Long id) {
        return new ResponseEntity<>(userDataManagementService.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/users/loggedIn")
    public ResponseEntity<?> updateUser(@RequestBody MyUserDTO userDTO) {
        userModificationService.updateMyUser(MyUserMapper.mapDTOtoMyUser(userWebAuthenticationService.getLoggedInUser(),userDTO));
        return ResponseEntity.ok().build();
    }
}
