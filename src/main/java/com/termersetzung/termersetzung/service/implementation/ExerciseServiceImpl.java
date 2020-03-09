package com.termersetzung.termersetzung.service.implementation;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;
import com.termersetzung.termersetzung.service.repository.ExerciseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            List<Exercise> exercises = (List<Exercise>) exerciseRepository.findAll();
            return exercises;
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

}