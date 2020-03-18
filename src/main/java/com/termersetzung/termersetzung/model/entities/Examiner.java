package com.termersetzung.termersetzung.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    @OneToOne(mappedBy = "examiner")
    private Exam exam;

    @JsonBackReference(value="exercise-examiner")
    @OneToOne(mappedBy = "examiner")
    private Exercise exercise;

    public Examiner() {
    }

    public Examiner(int id, String firstname, String lastname, Exam exam, Exercise exercise) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.exam = exam;
        this.exercise = exercise;
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    

}