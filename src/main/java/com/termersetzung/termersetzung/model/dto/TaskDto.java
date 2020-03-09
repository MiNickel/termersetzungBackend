package com.termersetzung.termersetzung.model.dto;

/**
 * TaskDto
 */
public class TaskDto {

    private int id;

    private String name;

    private String description;

    private String startTerm;

    private int examId;

    private int exerciseId;

    private int score;

    public TaskDto() {

    }

    public TaskDto(int id, String name, String description, String startTerm, int examId, int exerciseId, int score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTerm = startTerm;
        this.examId = examId;
        this.exerciseId = exerciseId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(String startTerm) {
        this.startTerm = startTerm;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    
}