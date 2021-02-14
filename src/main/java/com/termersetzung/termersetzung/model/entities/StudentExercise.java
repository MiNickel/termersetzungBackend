package com.termersetzung.termersetzung.model.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * StudentExercise
 */
@Entity
public class StudentExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference(value = "student-exercise-task")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentExercise", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Task> tasks;

    @JsonBackReference(value = "student-exercises")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @JsonBackReference(value = "studentExercise")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentExercise() {

    }

    public StudentExercise(int id, List<Task> tasks, Exercise exercise, Student student) {
        this.id = id;
        this.tasks = tasks;
        this.exercise = exercise;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setStudentExercise(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setStudentExercise(null);
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
