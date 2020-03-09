package com.termersetzung.termersetzung.service.implementation;

import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.service.interfaces.ExamService;
import com.termersetzung.termersetzung.service.repository.ExamRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Die Klausur konnte nicht gefunden werden.");
        }
        return exam;

    }

    @Override
    public Exam uploadExam(Exam exam) {

        try {
            String code = RandomStringUtils.random(6, true, true);
            exam.setCode(code);
    
            exam = examRepository.save(exam);
            return exam;
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        
    }

    @Override
    public Exam getExamById(int id) {
        Exam exam = examRepository.findById(id).orElse(null);

        if (exam == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Die Klausur konnte nicht gefunden werden.");
        }

        return exam;
    }

}