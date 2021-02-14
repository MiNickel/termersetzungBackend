package com.termersetzung.termersetzung.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Examiner
 */
@Entity
public class Examiner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @JsonBackReference(value="exam-examiner")
    @OneToMany(mappedBy = "examiner")
    private List<Exam> exams;

    @JsonBackReference(value="exercise-examiner")
    @OneToMany(mappedBy = "examiner")
    private List<Exercise> exercises;

    public Examiner() {
    }

    public Examiner(int id, String firstname, String lastname, List<Exam> exams, List<Exercise> exercises) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.exams = exams;
        this.exercises = exercises;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    

}