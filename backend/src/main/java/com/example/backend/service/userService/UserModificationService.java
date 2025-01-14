package com.example.backend.service.userService;

import com.example.backend.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserModificationService {

    @Autowired
    private MyUserRepository userRepository;

    public boolean changePassword(Long userId, String newPassword) {
        return userRepository.findById(userId).map(user -> {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}

