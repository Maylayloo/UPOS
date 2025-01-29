package com.example.backend.mapper;

import com.example.backend.dto.ExamDTO;
import com.example.backend.model.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;



public class ExamMapper {

    public ExamDTO toDTO(Exam exam){
        ExamDTO examDTO = new ExamDTO();
        examDTO.setAttempt(exam.getAttempt());
        examDTO.setPlace(exam.getPlace());
        examDTO.setCourseId(exam.getCourseId());
        examDTO.setDate(exam.getDate());
        examDTO.setProfessorId(exam.getProfessorId());
        return examDTO;


    }
    public Exam toEntity(ExamDTO examDTO){
        Exam exam = new Exam();
        exam.setAttempt(examDTO.getAttempt());
        exam.setPlace(examDTO.getPlace());
        exam.setCourseId(examDTO.getCourseId());
        exam.setDate(examDTO.getDate());
        exam.setProfessorId(examDTO.getProfessorId());
        return exam;

    }
}

