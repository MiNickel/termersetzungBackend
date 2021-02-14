package com.termersetzung.termersetzung.controller;

import java.util.ArrayList;
import java.util.List;

import com.termersetzung.termersetzung.model.dto.ExerciseDto;
import com.termersetzung.termersetzung.model.dto.StepCheckDto;
import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.model.entities.Step;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.ExaminerService;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;

import com.termersetzung.termersetzung.service.interfaces.StudentExerciseService;
import com.termersetzung.termersetzung.service.interfaces.StudentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ExerciseController
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    StudentExerciseService studentExerciseService;

    @Autowired
    ExaminerService examinerService;

    @Autowired
    StudentService studentService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(path = "/examiner/{examinerId}")
    public List<ExerciseDto> getAllExercisesForExaminer(@PathVariable(value = "examinerId") int examinerId) {
        List<Exercise> exercises = exerciseService.getAllExercisesForExaminer(examinerId);
        List<ExerciseDto> exerciseDtos = new ArrayList<>();
        for (Exercise exercise : exercises) {
            exerciseDtos.add(modelMapper.map(exercise, ExerciseDto.class));
        }

        return exerciseDtos;
    }

    @GetMapping(path = "/student")
    public List<ExerciseDto> getAllExercises() {
        List <Exercise> exercises = exerciseService.getAllExercises();
        List <ExerciseDto> exerciseDtos = new ArrayList<>();
        for (Exercise exercise : exercises) {
            exerciseDtos.add(modelMapper.map(exercise, ExerciseDto.class));
        }
        return exerciseDtos;
    }

    @GetMapping(path = "/{id}")
    public ExerciseDto getExerciseByIdForExaminer(@PathVariable(value = "id") int id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return modelMapper.map(exercise, ExerciseDto.class);
    }

    @GetMapping(path = "/student/{id}")
    public ExerciseDto getExerciseByIdForStudent(@PathVariable(value = "id") int id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return modelMapper.map(exercise, ExerciseDto.class);
    }

    @PostMapping(path = "")
    public void uploadExercise(@RequestBody ExerciseDto exerciseDto) {
        Exercise exercise = mapExerciseDtoToExercise(exerciseDto);
        exerciseService.uploadExercise(exercise);
    }

    @PostMapping(path = "/check")
    public List<StepCheckDto> checkSteps(@RequestBody List<StepCheckDto> stepList) {
        return exerciseService.checkSteps(stepList);
    }

    

    private Exercise mapExerciseDtoToExercise(ExerciseDto exerciseDto) {
        Exercise exercise = modelMapper.map(exerciseDto, Exercise.class);
        List<Task> tasks = exercise.getTasks();
        exercise.setTasks(new ArrayList<>());
        for (Task task : tasks) {
            List<Step> steps = task.getSteps();
            task.setSteps(new ArrayList<>());
            for (Step step : steps) {
                task.addStep(step);
            }
            task.setExam(null);
            task.setStudentExam(null);
            task.setStudentExercise(null);
            exercise.addTask(task);
        }
        exercise.setExaminer(examinerService.findById(exerciseDto.getExaminerId()));
        return exercise;
    }
}
