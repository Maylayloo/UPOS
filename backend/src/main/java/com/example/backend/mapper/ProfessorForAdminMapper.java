package com.example.backend.mapper;

import com.example.backend.dto.ProfessorDTO;
import com.example.backend.dto.wrapper.ProfessorForAdminWrapperDTO;
import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;

public class ProfessorForAdminMapper {
    public static ProfessorForAdminWrapperDTO toDTO(Professor professor, MyUser myuser)
    {
       ProfessorForAdminWrapperDTO dto = new ProfessorForAdminWrapperDTO();
       ProfessorDTO professorDTO = new ProfessorDTO();
       dto.setProfessorDTO(professorDTO);
       dto.getProfessorDTO().setTitle(professor.getTitle());
       dto.getProfessorDTO().setSurname(myuser.getSurname());
       dto.getProfessorDTO().setName(myuser.getName());
       dto.setProfessorId(professor.getProfessorId());
       return dto;
    }
}
