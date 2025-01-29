package com.example.backend.service.professorService;

import com.example.backend.dto.ExamDTO;
import com.example.backend.mapper.ExamMapper;
import com.example.backend.model.Course;
import com.example.backend.model.Exam;
import com.example.backend.model.Professor;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamManagementForProfessorService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ProfessorAuthenticationService professorAuthenticationService;


    public void scheduleExam(Long courseId, Exam exam) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        course.addExam(exam);
        courseRepository.save(course);
    }
    public void scheduleExam(ExamDTO examDTO) {
        ExamMapper examMapper=new ExamMapper();
        Exam exam = examMapper.toEntity(examDTO);
        examRepository.save(exam);
    }

    public void modifyExamById(Long examId, ExamDTO examDTO) {
        Exam existingExam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam with ID " + examId + " not found"));

        existingExam.setCourseId(examDTO.getCourseId());
        existingExam.setProfessorId(examDTO.getProfessorId());
        existingExam.setDate(examDTO.getDate());
        existingExam.setPlace(examDTO.getPlace());
        existingExam.setAttempt(examDTO.getAttempt());
        examRepository.save(existingExam);
    }

    public void modifyExamById(Long examId, Exam updatedExam) {
        Exam existingExam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam with ID " + examId + " not found"));

        existingExam.setDate(updatedExam.getDate());
        existingExam.setPlace(updatedExam.getPlace());
        existingExam.setAttempt(updatedExam.getAttempt());
        existingExam.setProfessorId(updatedExam.getProfessorId());
        existingExam.setCourseId(updatedExam.getCourseId()); // Update course if necessary
        examRepository.save(existingExam);
    }

    public List<Exam> getExamsByLoggedInProfessor() {
        Professor loggedInProfessor = professorAuthenticationService.getLoggedInProfessor();
        if (loggedInProfessor == null) {
            throw new RuntimeException("No professor is currently logged in");
        }

        return examRepository.findByProfessorId(loggedInProfessor.getProfessorId());
    }

    public void deleteExamById(Long examId) {
        examRepository.deleteById(examId);
    }
}





































































