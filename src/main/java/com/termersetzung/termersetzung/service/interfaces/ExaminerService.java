package com.termersetzung.termersetzung.service.interfaces;

import com.termersetzung.termersetzung.model.entities.Examiner;

public interface ExaminerService {

    public Examiner findByFirstnameAndLastname(String firstname, String lastname);

    public Examiner saveExaminer(String firstname, String lastname);

    public Examiner findById(int id);
}