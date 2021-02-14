package com.termersetzung.termersetzung.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentExerciseDto {
    private int id;
    private List<TaskDto> tasks;
    private int exerciseId;
    private int studentId;
}
