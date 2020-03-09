package com.termersetzung.termersetzung.service.repository;

import java.util.Optional;

import com.termersetzung.termersetzung.model.entities.Exam;

import org.springframework.data.repository.CrudRepository;

/**
 * TestRepository
 */
public interface ExamRepository extends CrudRepository<Exam, Integer> {
    
    public Optional<Exam> findByCode(String code);

}