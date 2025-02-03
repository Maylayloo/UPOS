package com.example.backend.dto.wrapper;

import com.example.backend.dto.ProfessorDTO;

public class ProfessorForAdminWrapperDTO {
   private Long professorId;
   private String name;
   private String surname;
   private String title;

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
