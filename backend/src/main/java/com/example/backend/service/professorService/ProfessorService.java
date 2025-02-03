package com.example.backend.service.professorService;

import com.example.backend.dto.ProfessorDTO;
import com.example.backend.dto.wrapper.ProfessorForAdminWrapperDTO;
import com.example.backend.mapper.ProfessorForAdminMapper;
import com.example.backend.mapper.ProfessorMapper;
import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;
import com.example.backend.repository.MyUserRepository;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.service.userService.UserDataManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private UserDataManagementService userDataManagementService;
    @Autowired
    private ProfessorAuthenticationService professorAuthenticationService;
    @Autowired
    private MyUserRepository myUserRepository;
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


    public List<ProfessorForAdminWrapperDTO> getAll() {
      List<Professor> professors=  professorRepository.findAll();
      List<ProfessorForAdminWrapperDTO> result= new ArrayList<>();
      for (Professor professor: professors) {
          MyUser user=myUserRepository.findById(professor.getUserId()).orElse(null);
          ProfessorForAdminWrapperDTO resultObject= ProfessorForAdminMapper.toDTO(professor,user);
          result.add(resultObject);

      }
      return result;
    }
}
