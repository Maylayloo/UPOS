package com.example.backend.service.adminService;

import com.example.backend.dto.MyUserDTO;
import com.example.backend.dto.ProfessorDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.model.Professor;
import com.example.backend.model.Student;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementForAdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public void registerUser(Student student) {
        studentRepository.save(student);
    }


    public void registerUser(Professor professor) {
        professorRepository.save(professor);
    }

    public void killStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void killProfessor(Long professorId) {
        professorRepository.deleteById(professorId);
    }

    public void registerStudent(StudentDTO studentDTO, MyUserDTO myUserDTO) {
    }

    public void registerProfessor(ProfessorDTO professor, MyUserDTO myUserDTO) {}
}
