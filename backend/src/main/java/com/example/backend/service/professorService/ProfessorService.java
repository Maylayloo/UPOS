package com.example.backend.service.professorService;

import com.example.backend.dto.ProfessorDTO;
import com.example.backend.mapper.ProfessorMapper;
import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.service.userService.UserDataManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private UserDataManagementService userDataManagementService;
    @Autowired
    private ProfessorAuthenticationService professorAuthenticationService;
    public ProfessorDTO getProfessorDTOByProfessorId(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElse(null);
        MyUser user=userDataManagementService.getUserById(professor.getUserId()).get();
        ProfessorDTO professorDTO= ProfessorMapper.toDTO(professor, user);
        return professorDTO;
    }

    public ProfessorDTO getProfessorDTOByLoggedIn() {
        Professor professorAuth=professorAuthenticationService.getLoggedInProfessor();
        Professor professor = professorRepository.findById(professorAuth.getProfId()).orElse(null);
        MyUser user=userDataManagementService.getUserById(professor.getUserId()).get();
        ProfessorDTO professorDTO= ProfessorMapper.toDTO(professor, user);
        return professorDTO;
    }


}
