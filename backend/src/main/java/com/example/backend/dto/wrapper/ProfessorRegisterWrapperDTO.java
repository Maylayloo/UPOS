package com.example.backend.dto.wrapper;

import com.example.backend.dto.MyUserDTO;
import com.example.backend.dto.ProfessorDTO;
import com.example.backend.model.Professor;

public class ProfessorRegisterWrapperDTO {
    private MyUserDTO myUserDTO;
    private String Title;

    public MyUserDTO getMyUserDTO() {
        return myUserDTO;
    }

    public void setMyUserDTO(MyUserDTO myUserDTO) {
        this.myUserDTO = myUserDTO;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
