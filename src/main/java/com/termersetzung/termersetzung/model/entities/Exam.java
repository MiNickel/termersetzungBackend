package com.termersetzung.termersetzung.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

    @JsonManagedReference(value="exam-examiner")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private Examiner examiner;

    @JsonManagedReference(value = "exam-task")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exam", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Task> tasks;

    @Convert(converter = CryptoConverter.class)
    @Column(nullable = false)
    private String code;

    @OneToOne(mappedBy = "exam")
    private StudentExam studentExam;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    public Exam() {

    }

    public Exam(int id, String name, Examiner examiner, List<Task> tasks, String code, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.examiner = examiner;
        this.tasks = tasks;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Examiner getExaminer() {
        return examiner;
    }

    public void setExaminer(Examiner examiner) {
        this.examiner = examiner;
    }

    
    
    
}