package com.termersetzung.termersetzung.service.implementation;

import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.service.interfaces.ExamService;
import com.termersetzung.termersetzung.service.repository.ExamRepository;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TestServiceImpl
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamRepository examRepository;

    @Override
    public Exam getExam(String code) {

        Exam exam = examRepository.findByCode(code).orElse(null);

        if (exam == null) {
            return null;
        }
        return exam;
    }

    /*
     * @Override public List<Test> getAllExercises() { boolean exerciseBool = false;
     * List<Test> exercises = (List<Test>)
     * testRepository.findAllByIsExam(exerciseBool); return exercises; }
     */

    @Override
    public Exam uploadExam(Exam exam) {

        String code = RandomStringUtils.random(6, true, true);
        exam.setCode(code);

        exam = examRepository.save(exam);
        return exam;
    }

    @Override
    public Exam getExamById(int id) {
        return examRepository.findById(id).orElse(null);
    }

}