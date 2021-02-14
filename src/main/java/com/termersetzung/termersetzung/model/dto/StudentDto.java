package com.termersetzung.termersetzung.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class StudentDto {

    private int id;
    private String firstname;
    private String lastname;
    private int studentNumber;
    private List<Integer> studentExerciseIds;
    private List<Integer> studentExamIds;
}
