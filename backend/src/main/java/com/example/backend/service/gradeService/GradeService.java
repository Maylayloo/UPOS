package com.example.backend.service.gradeService;

import com.example.backend.model.Grade;
import com.example.backend.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> showGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}

