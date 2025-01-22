package com.example.backend.service.studentService;

import com.example.backend.model.Exam;
import com.example.backend.service.professorService.ExamManagementForProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamForStudentService {

    public List<Exam> getAllExamsByLoggedInStudent(){return null;}
}
