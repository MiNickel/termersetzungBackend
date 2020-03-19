package com.termersetzung.termersetzung.service.interfaces;

import com.termersetzung.termersetzung.model.entities.Exam;

import java.util.List;

/**
 * TestService
 */
public interface ExamService {

    public Exam getExam(String code);

    public Exam getExamById(int id);

    public Exam uploadExam(Exam exam);

    public List<Exam> getAllExams();
}
