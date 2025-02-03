package com.example.backend.mapper;

import com.example.backend.dto.ProfessorDTO;
import com.example.backend.dto.wrapper.ProfessorForAdminWrapperDTO;
import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;

public class ProfessorForAdminMapper {
    public static ProfessorForAdminWrapperDTO toDTO(Professor professor, MyUser myuser)
    {
       ProfessorForAdminWrapperDTO dto = new ProfessorForAdminWrapperDTO();
       dto.setName(myuser.getName());
       dto.setSurname(myuser.getSurname());
       dto.setProfessorId(professor.getProfessorId());
       dto.setTitle(professor.getTitle());
       return dto;
    }
}
