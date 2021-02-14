package com.termersetzung.termersetzung.service.interfaces;

import java.util.List;

import com.termersetzung.termersetzung.model.dto.StepCheckDto;
import com.termersetzung.termersetzung.model.entities.Exercise;

/**
 * ExerciseService
 */
public interface ExerciseService {

    public Exercise getExerciseById(int id);

    public List<Exercise> getAllExercisesForExaminer(int examinerId);

    public List<Exercise> getAllExercises();

    public Exercise uploadExercise(Exercise exercise);

    public List<StepCheckDto> checkSteps(List<StepCheckDto> stepList);
    
}