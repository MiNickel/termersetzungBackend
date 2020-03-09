package com.termersetzung.termersetzung.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.termersetzung.termersetzung.config.CryptoConverter;

/**
 * Test
 */
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String professor;

    @JsonManagedReference(value = "exam-task")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exam", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Task> tasks;

    @Convert(converter = CryptoConverter.class)
    @Column
    private String code;

    public Exam() {

    }

    public Exam(int id, String name, String professor, List<Task> tasks, String code) {
        this.id = id;
        this.name = name;
        this.professor = professor;
        this.tasks = tasks;
        this.code = code;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setExam(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setExam(null);
    }
    
    
}