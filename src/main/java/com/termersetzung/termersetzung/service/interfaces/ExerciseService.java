package com.termersetzung.termersetzung.service.interfaces;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Exercise;

/**
 * ExerciseService
 */
public interface ExerciseService {

    public Exercise getExerciseById(int id);

    public List<Exercise> getAllExercises();

    public Exercise uploadExercise(Exercise exercise);
    
}