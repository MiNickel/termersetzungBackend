package com.termersetzung.termersetzung.service.implementation;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;
import com.termersetzung.termersetzung.service.repository.ExerciseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ExerciseServiceImpl
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Override
    public Exercise getExerciseById(int id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        
        if (exercise == null) {
            return null;
        }
        return exercise;
    }

    @Override
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = (List<Exercise>) exerciseRepository.findAll();
        return exercises;
    }

    @Override
    public Exercise uploadExercise(Exercise exercise) {
        exercise = exerciseRepository.save(exercise);
        return exercise;
    }

    
}