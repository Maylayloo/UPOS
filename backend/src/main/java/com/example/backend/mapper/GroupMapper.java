package com.example.backend.mapper;

import com.example.backend.dto.MajorGroupDTO;
import com.example.backend.model.MajorGroup;

public class GroupMapper {
    public static MajorGroup toModel(MajorGroupDTO majorGroupDTO) {
        MajorGroup majorGroup = new MajorGroup();
        majorGroup.setNumberOfGroup(majorGroupDTO.getNumberOfGroup());
        majorGroup.setPlace(majorGroupDTO.getPlace());
       majorGroup.setType(majorGroupDTO.getType());
      majorGroup.setCourseId(majorGroupDTO.getCourseId());
      majorGroup.setStartOfLesson(majorGroupDTO.getStartOfLesson());
      majorGroup.setEndOfLesson(majorGroupDTO.getEndOfLesson());
      majorGroup.setDayOfTheWeek(majorGroupDTO.getDayOfTheWeek());
      majorGroup.setMaxStudentAmount(majorGroupDTO.getMaxStudentAmount());
      majorGroup.setProfessorId(majorGroupDTO.getProfessorId());
      return majorGroup;
    }
}