package com.example.backend.mapper;

import com.example.backend.dto.ProfessorDTO;
import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;

public class ProfessorMapper {
    public static  ProfessorDTO toDTO(Professor professor, MyUser user){
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setSurname(user.getSurname());
        professorDTO.setName(user.getName());
        professorDTO.setTitle(professor.getTitle());
        return professorDTO;
    }
}
