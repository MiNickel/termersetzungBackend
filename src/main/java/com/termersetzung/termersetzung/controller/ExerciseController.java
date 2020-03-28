package com.termersetzung.termersetzung.controller;

import java.util.List;

import com.termersetzung.termersetzung.model.dto.StepCheckDto;
import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExerciseController
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Exercise> getAllExercises() {
        List <Exercise> exercises = exerciseService.getAllExercises();
        return exercises;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Exercise getExerciseById(@PathVariable(value = "id") int id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return exercise;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Exercise uploadExercise(@RequestBody Exercise exercise) {
        exercise = exerciseService.uploadExercise(exercise);
        return exercise;
    }

    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public List<StepCheckDto> checkSteps(@RequestBody List<StepCheckDto> stepList) {
        return exerciseService.checkSteps(stepList);
    }
}
