package com.example.backend.service.studentService;

import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentAuthenticationService studentAuthenticationService;
    public Map<String,String> getNameAndSurnameByStudentId(Long studentId){
        return studentRepository.getNameAndSurnameByStudentId(studentId);
    }

    public Map<String,String> getNameAndSurnameByLoggedInStudent(){
        Student student=studentAuthenticationService.getLoggedInStudent();
        return studentRepository.getNameAndSurnameByStudentId(student.getStudentId());
    }


    public Student getStudentByStudentId(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
