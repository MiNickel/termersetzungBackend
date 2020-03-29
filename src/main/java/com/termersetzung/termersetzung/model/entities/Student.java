package com.termersetzung.termersetzung.model.entities;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "student")
    private StudentExercise studentExercise;

    public Student() {

    }

    public Student(int id, String firstname, String lastname, int studentNumber, StudentExercise studentExercise) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.studentNumber = studentNumber;
        this.studentExercise = studentExercise;
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

    public StudentExercise getStudentExercise() {
        return studentExercise;
    }

    public void setStudentExercise(StudentExercise studentExercise) {
        this.studentExercise = studentExercise;
    }
}
