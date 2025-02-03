package com.example.backend.dto.wrapper;

import com.example.backend.dto.ProfessorDTO;

public class ProfessorForAdminWrapperDTO {
   private Long professorId;
    private ProfessorDTO professorDTO;

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public ProfessorDTO getProfessorDTO() {
        return professorDTO;
    }

    public void setProfessorDTO(ProfessorDTO professorDTO) {
        this.professorDTO = professorDTO;
    }
}
