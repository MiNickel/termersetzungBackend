package com.termersetzung.termersetzung.service.repository;

import java.util.Optional;

import com.termersetzung.termersetzung.model.entities.Examiner;

import org.springframework.data.repository.CrudRepository;

public interface ExaminerRepository extends CrudRepository<Examiner, Integer> {

    public Optional<Examiner> findByFirstnameAndLastname(String firstname, String lastname);

}