package com.example.backend.dto.wrapper;

import com.example.backend.dto.MyUserDTO;
import com.example.backend.dto.StudentDTO;

public class StudentRegisterWrapperDTO {
    private MyUserDTO user;
    private StudentDTO student;

    public MyUserDTO getUser() {
        return user;
    }

    public void setUser(MyUserDTO user) {
        this.user = user;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
}
