package com.termersetzung.termersetzung.service.implementation;

import java.util.List;

import com.termersetzung.termersetzung.model.dto.StepCheckDto;
import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;
import com.termersetzung.termersetzung.service.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.termersetzung.termersetzung.service.implementation.SharedMethodsImpl.applyTransformCheck;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Die Klausur konnte nicht gefunden werden.");
        }
        return exercise;
    }

    @Override
    public List<Exercise> getAllExercises() {
        try {
            return (List<Exercise>) exerciseRepository.findAll();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }

    @Override
    public Exercise uploadExercise(Exercise exercise) {
        try {
            exercise = exerciseRepository.save(exercise);
            return exercise;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }

    @Override
    public List<StepCheckDto> checkSteps(List<StepCheckDto> stepList) {
        for (StepCheckDto step : stepList) {
            String startEquation = step.getStartEquation();
            String rule = "f -> f" + step.getRule();
            String targetEquation = step.getTargetEquation();
            boolean isCorrect = applyTransformCheck(startEquation, rule, targetEquation);

            step.setCorrect(isCorrect);
        }
        return stepList;
    }

}
