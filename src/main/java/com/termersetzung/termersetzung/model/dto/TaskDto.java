package com.termersetzung.termersetzung.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskDto {

    private int id;
    private String name;
    private String description;
    private String notes;
    private List<StepDto> steps;
    private int examId;
    private int exerciseId;
    private int studentExamId;
    private int studentExerciseId;
    private int score;

}
