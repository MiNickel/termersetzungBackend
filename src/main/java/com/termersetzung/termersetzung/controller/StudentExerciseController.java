package com.termersetzung.termersetzung.controller;

import java.util.ArrayList;
import java.util.List;

import com.termersetzung.termersetzung.model.dto.StepDto;
import com.termersetzung.termersetzung.model.dto.StudentExerciseDto;
import com.termersetzung.termersetzung.model.dto.TaskDto;
import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.model.entities.Step;
import com.termersetzung.termersetzung.model.entities.StudentExercise;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;
import com.termersetzung.termersetzung.service.interfaces.StudentExerciseService;
import com.termersetzung.termersetzung.service.interfaces.StudentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/studentexercise")
public class StudentExerciseController {

    @Autowired
    StudentExerciseService studentExerciseService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    StudentService studentService;

    @Autowired
    ModelMapper modelMapper;
    
    @GetMapping(path = "/{studentId}")
    public List<StudentExerciseDto> getAllExercisesForStudent(@PathVariable(value = "studentId") int studentId) {
        List<StudentExercise> studentExercises = studentExerciseService.findAllByStudentId(studentId);
        List<StudentExerciseDto> studentExerciseDtos = new ArrayList<>();
        for (StudentExercise studentExercise : studentExercises) {
            studentExerciseDtos.add(modelMapper.map(studentExercise, StudentExerciseDto.class));
        }

        return studentExerciseDtos;
    }

    @GetMapping(path = "/{exerciseId}/{studentId}")
    public StudentExerciseDto getStudentExerciseByIdAndStudentId(@PathVariable(value = "exerciseId") int exerciseId,
            @PathVariable(value = "studentId") int studentId) {
        StudentExercise studentExercise = studentExerciseService.findByExerciseIdAndStudentId(exerciseId, studentId);
        if (studentExercise == null) {
            Exercise exercise = exerciseService.getExerciseById(exerciseId);
            StudentExerciseDto studentExerciseDto = mapExerciseToStudentExerciseDto(exercise);
            return studentExerciseDto;
        }
        return modelMapper.map(studentExercise, StudentExerciseDto.class);
    }

    @PostMapping(path = "/")
    public void uploadStudentExercise(@RequestBody StudentExerciseDto studentExerciseDto) {
        StudentExercise studentExercise = mapStudentExerciseDtoToStudentExercise(studentExerciseDto);
        studentExerciseService.uploadStudentExercise(studentExercise);
    }

    private StudentExercise mapStudentExerciseDtoToStudentExercise(StudentExerciseDto studentExerciseDto) {
        StudentExercise studentExercise = modelMapper.map(studentExerciseDto, StudentExercise.class);
        List<Task> tasks = studentExercise.getTasks();
        studentExercise.setTasks(new ArrayList<>());
        for (Task task : tasks) {
            List<Step> steps = task.getSteps();
            task.setSteps(new ArrayList<>());
            for (Step step : steps) {
                task.addStep(step);
            }
            task.setExercise(null);
            task.setExam(null);
            task.setStudentExam(null);
            studentExercise.addTask(task);
        }
        studentExercise.setStudent(studentService.findById(studentExerciseDto.getStudentId()));
        return studentExercise;
    }

    private StudentExerciseDto mapExerciseToStudentExerciseDto(Exercise exercise) {
        StudentExerciseDto studentExerciseDto = modelMapper.map(exercise, StudentExerciseDto.class);
        studentExerciseDto.setId(-1);
        studentExerciseDto.setStudentId(-1);
        studentExerciseDto.setTasks(new ArrayList<>());
        List<Task> tasks = exercise.getTasks();
        for (Task task : tasks) {
            TaskDto taskDto = modelMapper.map(task, TaskDto.class);
            taskDto.setId(-1);
            taskDto.setExamId(0);
            taskDto.setExerciseId(0);
            taskDto.setStudentExerciseId(-1);
            taskDto.setStudentExamId(0);
            Step step = task.getSteps().get(0);
            taskDto.setSteps(new ArrayList<>());
            StepDto stepDto = modelMapper.map(step, StepDto.class);
            stepDto.setConversion("");
            stepDto.setScore(0);
            stepDto.setId(-1);
            stepDto.setTaskId(-1);
            taskDto.getSteps().add(stepDto);
            studentExerciseDto.getTasks().add(taskDto);
        }

        return studentExerciseDto;
    }
}