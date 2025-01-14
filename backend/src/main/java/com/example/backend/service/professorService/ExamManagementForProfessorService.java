package com.example.backend.service.professorService;

import com.example.backend.model.Course;
import com.example.backend.model.Exam;
import com.example.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamManagementForProfessorService {
    @Autowired
    private CourseRepository courseRepository;

    public void scheduleExam(Long courseId, Exam exam) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        course.addExam(exam);
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

}





































































