package com.termersetzung.termersetzung.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Task
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private String startTerm;

    @Column(name = "steps")
    @ElementCollection
    private List<String> steps = new ArrayList<>();

    @Column(nullable = false)
    private String endTerm;

    @JsonBackReference(value = "exam-task")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exam_id")
    private Exam exam;

    @JsonBackReference(value = "exercise-task")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="exercise_id")
    private Exercise exercise;

    @Column(nullable = false)
    private int score;

    public Task() {

    }

    public Task(int id, String name, String description, String startTerm, List<String> steps, String endTerm, Exam exam, Exercise exercise, int score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTerm = startTerm;
        this.steps = steps;
        this.endTerm = endTerm;
        this.exam = exam;
        this.exercise = exercise;
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

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(String endTerm) {
        this.endTerm = endTerm;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}