package com.termersetzung.termersetzung.service.interfaces;

import com.termersetzung.termersetzung.model.entities.StudentExam;

import java.util.List;

/**
 * StudentExamService
 */
public interface StudentExamService {

    public StudentExam correctStudentExam(StudentExam studentExam);

    public List<StudentExam> getAllStudentExams();

    public List<StudentExam> getAllStudentExamsWithExamId(int examId);

    public StudentExam getStudentExamById(int studentExamId);
    
}
