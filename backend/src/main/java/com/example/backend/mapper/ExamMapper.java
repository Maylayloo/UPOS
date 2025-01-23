package com.example.backend.mapper;

import com.example.backend.dto.ExamDTO;
import com.example.backend.model.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExamMapper {
    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    ExamDTO examToExamDTO(Exam exam);
    Exam examDTOToExam(ExamDTO examDTO);
}
