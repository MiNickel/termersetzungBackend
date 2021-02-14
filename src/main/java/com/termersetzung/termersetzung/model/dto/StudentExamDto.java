package com.termersetzung.termersetzung.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentExamDto {
    private int id;
    private List<TaskDto> tasks;
    private int examId;
    private int studentId;
}
