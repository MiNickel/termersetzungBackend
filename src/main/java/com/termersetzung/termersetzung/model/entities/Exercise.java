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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Exercise
 */
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @JsonManagedReference(value = "exercise-examiner")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private Examiner examiner;

    @Column(nullable = false)
    private String category;

    @JsonManagedReference(value = "exercise-task")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exercise", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Task> tasks;

    @OneToOne(mappedBy = "exercise")
    private StudentExercise studentExercise;

    public Exercise() {

    }

    public Exercise(int id, String name, Examiner examiner, String category, List<Task> tasks, StudentExercise studentExercise) {
        this.id = id;
        this.name = name;
        this.examiner = examiner;
        this.category = category;
        this.tasks = tasks;
        this.studentExercise = studentExercise;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setExercise(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setExercise(null);
    }

    public Examiner getExaminer() {
        return examiner;
    }

    public void setExaminer(Examiner examiner) {
        this.examiner = examiner;
    }

    public StudentExercise getStudentExercise() {
        return studentExercise;
    }

    public void setStudentExercise(StudentExercise studentExercise) {
        this.studentExercise = studentExercise;
    }
}
