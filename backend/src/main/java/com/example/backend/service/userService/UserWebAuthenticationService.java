package com.example.backend.service.userService;

import com.example.backend.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserWebAuthenticationService {
    @Autowired
    private UserDataManagementService userDataManagementService;
    public void rememberMe(){}
    public MyUser getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=  (User)authentication.getPrincipal();
        String email= user.getUsername();
        return userDataManagementService.getUserByEmail(email);

    }
}
