package com.termersetzung.termersetzung.controller;

import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.service.interfaces.ExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    ExamService examService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Exam getExam(@RequestParam(required = true) String code) {
        Exam exam = examService.getExam(code);
        return exam;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Exam getExamById(@PathVariable(value = "id") int id) {
        return examService.getExamById(id);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Exam uploadExam(@RequestBody Exam exam) {
        exam = examService.uploadExam(exam);
        return exam;
    }
}