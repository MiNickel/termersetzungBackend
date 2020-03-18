package com.termersetzung.termersetzung.model.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * ExamDto
 */
public class ExamDto {

    @NotNull
    private int id;

    private String name;

    private String professor;

    private List<TaskDto> tasks;

    private String code;

    private Date startDate;

    private Date endDate;

    public ExamDto() {

    }

    public ExamDto(int id, String name, String professor, List<TaskDto> tasks, String code, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.professor = professor;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    
    
}