package com.termersetzung.termersetzung.model.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * StudentExam
 */
@Entity
public class StudentExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference(value = "student-exam-task")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentExam", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Task> tasks;

    @JsonBackReference(value = "student-exams")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;

    @JsonBackReference(value = "studentExam")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public StudentExam() {

    }

    public StudentExam(int id, List<Task> tasks, Exam exam, Student student) {
        this.id = id;
        this.tasks = tasks;
        this.exam = exam;
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
        task.setStudentExam(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setStudentExam(null);
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
