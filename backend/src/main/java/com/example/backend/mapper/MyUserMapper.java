package com.example.backend.mapper;

import com.example.backend.dto.MyUserDTO;
import com.example.backend.model.MyUser;
import com.example.backend.service.authenticationService.AuthenticationService;
import com.example.backend.service.userService.UserDataManagementService;
import com.example.backend.service.userService.UserModificationService;
import com.example.backend.service.userService.UserWebAuthenticationService;
import com.example.backend.util.PasswordUtils;

public class MyUserMapper {


    public static MyUser mapDTOtoMyUser(MyUser myUser,MyUserDTO myUserDTO){
        myUser.setAddress(myUserDTO.getAddress());
        myUser.setPassword(PasswordUtils.encodePassword(myUserDTO.getPassword()));
        myUser.setName(myUserDTO.getName());
        myUser.setBankNumber(String.valueOf(myUserDTO.getBankNumber()));
        myUser.setSurname(myUserDTO.getSurname());
        myUser.setNumber(String.valueOf(myUserDTO.getNumber()));
        myUser.setRole(myUserDTO.getRole());
        myUser.setEmail(myUserDTO.getEmail());
        return myUser;
    }

    public static MyUser toEntity(MyUserDTO myUserDTO){
        MyUser myUser = new MyUser();
        myUser.setName(myUserDTO.getName());
        myUser.setBankNumber(myUserDTO.getBankNumber());
        myUser.setSurname(myUserDTO.getSurname());
        myUser.setNumber(myUserDTO.getNumber());
        myUser.setPassword(PasswordUtils.encodePassword(myUserDTO.getPassword()));
        myUser.setAddress(myUserDTO.getAddress());
        myUser.setRole(myUserDTO.getRole());
        myUser.setEmail(myUserDTO.getEmail());
        return myUser;
    }

}
