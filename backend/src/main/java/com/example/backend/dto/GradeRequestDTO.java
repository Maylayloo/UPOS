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

    public Long getStudentId() {return studentId;}

    public void setStudentId(Long studentId) {this.studentId = studentId;}

    public Long getGroupId() {return groupId;}

    public void setGroupId(Long groupId) {this.groupId = groupId;}

    public String getValue() {return value;}

    public void setValue(String value) {this.value = value;}

    public boolean isPartial() {return isPartial;}

    public void setPartial(boolean isPartial) {this.isPartial = isPartial;}
}
