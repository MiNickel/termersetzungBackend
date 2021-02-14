package com.termersetzung.termersetzung.model.dto;

import lombok.Data;

@Data
public class StepDto {

    private int id;
    private String step;
    private int score;
    private String conversion;
    private int taskId;
}
