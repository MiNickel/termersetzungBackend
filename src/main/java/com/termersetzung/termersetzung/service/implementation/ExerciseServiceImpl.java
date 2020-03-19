package com.termersetzung.termersetzung.service.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.termersetzung.termersetzung.model.dto.StepCheckDto;
import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;
import com.termersetzung.termersetzung.service.repository.ExerciseRepository;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
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

    @Override
    public List<StepCheckDto> checkSteps(List<StepCheckDto> stepList) {
        for (int i = 0; i < stepList.size(); i++) {
            StepCheckDto step = stepList.get(i);
            String startEquation = step.getStartEquation();
            String rule = "f -> f" + step.getRule();
            String targetEquation = step.getTargetEquation();
            boolean isCorrect = applyTransformCheck(startEquation, rule, targetEquation);

            stepList.get(i).setCorrect(isCorrect);
        }
        return stepList;
    }

}
