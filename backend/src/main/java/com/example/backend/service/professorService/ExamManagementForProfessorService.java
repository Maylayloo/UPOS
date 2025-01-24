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
import com.example.backend.service.professorService.ProfessorAuthenticationService;

import java.util.List;
import java.util.stream.Collectors;

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
    public void scheduleExam(Long courseId, ExamDTO examDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        course.addExam(ExamMapper.INSTANCE.examDTOToExam(examDTO));
        courseRepository.save(course);
    }

    public void modifyExamById(Long courseId, Long examId, Exam updatedExam) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        boolean updated = false;
        for (Exam exam : course.getExams()) {
            if (exam.getExamId() != null && exam.getExamId().equals(examId)) {
                exam.setDate(updatedExam.getDate());
                exam.setPlace(updatedExam.getPlace());
                updated = true;
                break;
            }
        }

        if (updated) {
            courseRepository.save(course);
        } else {
            throw new RuntimeException("Exam not found with ID: " + examId + " in course ID: " + courseId);
        }
    }
    public List<Exam> getExamsByLoggedInProfessor() {
        Professor loggedInProfessor = professorAuthenticationService.getLoggedInProfessor();
        if (loggedInProfessor == null) {
            throw new RuntimeException("No professor is currently logged in");
        }

        return examRepository.findByProfessorId(loggedInProfessor.getProfessorId());
    }
}





































































