package com.example.backend.service.professorService;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;
import com.example.backend.model.Role;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.service.userService.UserWebAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.exception.UnauthorizedException;

@Service
public class ProfessorAuthenticationService {

    @Autowired
    private UserWebAuthenticationService userWebAuthenticationService;

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor getLoggedInProfessor() {
        MyUser loggedInUser = userWebAuthenticationService.getLoggedInUser();

        if (loggedInUser.getRole() != Role.PROFESSOR) {
            throw new UnauthorizedException("Access denied. Logged-in user is not a professor.");
        }

        return professorRepository.findByUserId(loggedInUser.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find professor associated with the user."));
    }
}
