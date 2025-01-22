package com.example.backend.mapper;

import com.example.backend.dto.GradeRequestDTO;
import com.example.backend.model.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    GradeRequestDTO toGradeRequestDTO(Grade grade);
    Grade toGrade(GradeRequestDTO gradeRequestDTO);
}
