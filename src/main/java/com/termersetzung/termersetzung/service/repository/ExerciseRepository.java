package com.termersetzung.termersetzung.service.repository;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Exercise;

import org.springframework.data.repository.CrudRepository;

/**
 * ExerciseRepository
 */
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
    
    List<Exercise> findAllByExaminerId(int examinerId);
}