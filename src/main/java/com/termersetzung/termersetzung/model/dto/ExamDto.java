package com.termersetzung.termersetzung.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ExamDto
 */
@Data
public class ExamDto {

    private int id;
    private String name;
    private int examinerId;
    private List<TaskDto> tasks;
    private String code;
    private Date startDate;
    private Date endDate;
    private List<Integer> studentExamIds;
}
