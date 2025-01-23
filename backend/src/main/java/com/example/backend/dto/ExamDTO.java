package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamDTO {
    private Long courseId;
    private String date;
    private String place;
}
