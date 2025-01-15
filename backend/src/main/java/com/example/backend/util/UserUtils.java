package com.example.backend.util;

import com.example.backend.service.userService.UserDataManagementService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class UserUtils {

    private static  final UserWebAuthenticationService userWebAuthenticationService = new UserWebAuthenticationService();


    public static String getLoggedInUserRole(){
        return userWebAuthenticationService.getLoggedInUser().getRole().toString();
    }
    public static boolean isUserRoleStudent(){
        return getLoggedInUserRole().equals("STUDENT");
    }
    public static boolean isUserRoleAdmin(){
        return getLoggedInUserRole().equals("ADMIN");
    }
}
