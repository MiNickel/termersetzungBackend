package com.termersetzung.termersetzung.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Student
 */
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private int studentNumber;

    @JsonManagedReference(value = "studentExercise")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentExercise> studentExercises;

    @JsonManagedReference(value = "studentExam")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentExam> studentExams;

    public Student() {
    }

    public Student(int id, String firstname, String lastname, int studentNumber, List<StudentExercise> studentExercises, List<StudentExam> studentExams) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.studentNumber = studentNumber;
        this.studentExercises = studentExercises;
        this.studentExams = studentExams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
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

    public List<StudentExercise> getStudentExercises() {
        return studentExercises;
    }

    public void setStudentExercises(List<StudentExercise> studentExercises) {
        this.studentExercises = studentExercises;
    }

    public List<StudentExam> getStudentExams() {
        return studentExams;
    }

    public void setStudentExams(List<StudentExam> studentExams) {
        this.studentExams = studentExams;
    }
}
