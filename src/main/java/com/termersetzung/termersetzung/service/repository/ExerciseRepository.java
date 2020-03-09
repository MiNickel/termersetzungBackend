package com.termersetzung.termersetzung.service.repository;

import com.termersetzung.termersetzung.model.entities.Exercise;

import org.springframework.data.repository.CrudRepository;

/**
 * ExerciseRepository
 */
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
    
}