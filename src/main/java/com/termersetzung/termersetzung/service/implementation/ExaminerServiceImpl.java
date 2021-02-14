package com.termersetzung.termersetzung.service.implementation;

import com.termersetzung.termersetzung.model.entities.Examiner;
import com.termersetzung.termersetzung.service.interfaces.ExaminerService;
import com.termersetzung.termersetzung.service.repository.ExaminerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    @Autowired
    ExaminerRepository examinerRepository;

    @Override
    public Examiner findByFirstnameAndLastname(String firstname, String lastname) {
        return examinerRepository.findByFirstnameAndLastname(firstname, lastname).orElse(null);
    }

    @Override
    public Examiner saveExaminer(String firstname, String lastname) {
        Examiner saveExaminer = new Examiner(-1, firstname, lastname, null, null);
        return examinerRepository.save(saveExaminer);
    }

    @Override
    public Examiner findById(int id) {
        return examinerRepository.findById(id).orElse(null);
    }

}