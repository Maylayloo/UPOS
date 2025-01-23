package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GradeRequestDTO {
    private Long studentId;
    private Long groupId;
    private String value;
    private boolean isPartial;

}
