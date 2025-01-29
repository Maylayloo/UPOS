package com.example.backend.service.adminService;

import com.example.backend.dto.MyUserDTO;
import com.example.backend.dto.ProfessorDTO;
import com.example.backend.dto.StudentDTO;
import com.example.backend.dto.wrapper.StudentRegisterWrapperDTO;
import com.example.backend.mapper.MyUserMapper;
import com.example.backend.mapper.ProfessorMapper;
import com.example.backend.mapper.StudentMapper;
import com.example.backend.model.MyUser;
import com.example.backend.model.Professor;
import com.example.backend.model.Student;
import com.example.backend.repository.MyUserRepository;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementForAdminService {
    @Autowired
    private MyUserRepository myUserRepository;
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

    public void registerStudent(StudentRegisterWrapperDTO studentRegisterWrapperDTO) {
        MyUser myUser = MyUserMapper.toEntity(studentRegisterWrapperDTO.getUser());
        Student student = StudentMapper.toModel(studentRegisterWrapperDTO.getStudent());
        myUserRepository.save(myUser);
        student.setUserId(myUser.getUserId());
        studentRepository.save(student);

    }

    public void registerProfessor(String title, MyUserDTO myUserDTO) {
        MyUser myUser = MyUserMapper.toEntity(myUserDTO);
        Professor professor= new Professor();
        professor.setTitle(title);
        myUserRepository.save(myUser);
        professor.setUserId(myUser.getUserId());
        professorRepository.save(professor);
    }
}
