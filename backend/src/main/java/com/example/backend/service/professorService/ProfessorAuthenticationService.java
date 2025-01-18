package com.example.backend.service.professorService;

import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorAuthenticationService {

    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor getLoggedInProfessor() {
        MyUser loggedInUser = userWebAuthenticationService.getLoggedInUser();
        return professorRepository.findByUserId(loggedInUser.getUserId())
                .orElseThrow(() -> new RuntimeException("Logged-in user is not a professor"));
    }
}
