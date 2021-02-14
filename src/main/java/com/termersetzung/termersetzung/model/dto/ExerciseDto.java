package com.termersetzung.termersetzung.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class ExerciseDto {

    private int id;
    private String name;
    private int examinerId;
    private String category;
    private List<TaskDto> tasks;
    private List<Integer> studentExerciseIds;
}
