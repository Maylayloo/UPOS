package com.example.backend.service.userService;

import com.example.backend.model.MyUser;
import com.example.backend.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataManagementService {
    @Autowired
    private MyUserRepository MyUserRepository;

    public MyUser getUserByEmail(String email){
        return MyUserRepository.findByEmail(email).isPresent() ? MyUserRepository.findByEmail(email).get() : null;
    }
    public Optional<List<MyUser>> getAllUsers(){
        return Optional.of(MyUserRepository.findAll());
    }

    public Optional<MyUser> getUserById(Long id){return MyUserRepository.findById(id);}
}
