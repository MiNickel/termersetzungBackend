package com.termersetzung.termersetzung.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference(value = "task-steps")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Step> steps;

    @JsonBackReference(value = "exam-task")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @JsonBackReference(value = "exercise-task")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @JsonBackReference(value = "student-exam-task")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentexam_id")
    private StudentExam studentExam;

    @JsonBackReference(value = "student-exercise-task")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentexercise_id")
    private StudentExercise studentExercise;

    @Column
    private int score;

    public Task() {

    }

    public Task(int id, String name, String description, List<Step> steps, Exam exam,
            Exercise exercise, StudentExam studentExam, StudentExercise studentExercise, int score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.steps = steps;
        this.exam = exam;
        this.exercise = exercise;
        this.studentExam = studentExam;
        this.studentExercise = studentExercise;
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

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Exam getExam() {
        return exam;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void addStep(Step step) {
        this.steps.add(step);
        step.setTask(this);
    }

    public void removeStep(Step step) {
        this.steps.remove(step);
        step.setTask(null);
    }

    public StudentExam getStudentExam() {
        return studentExam;
    }

    public void setStudentExam(StudentExam studentExam) {
        this.studentExam = studentExam;
    }

    public StudentExercise getStudentExercise() {
        return studentExercise;
    }

    public void setStudentExercise(StudentExercise studentExercise) {
        this.studentExercise = studentExercise;
    }

}