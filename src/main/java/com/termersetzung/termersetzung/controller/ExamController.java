package com.termersetzung.termersetzung.controller;

import com.termersetzung.termersetzung.model.dto.ExamDto;
import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.model.entities.Step;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.ExamService;
import com.termersetzung.termersetzung.service.interfaces.ExaminerService;
import com.termersetzung.termersetzung.service.interfaces.StudentExamService;
import com.termersetzung.termersetzung.service.interfaces.StudentService;
import com.termersetzung.termersetzung.service.interfaces.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TestController
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StudentExamService studentExamService;

    @Autowired
    ExaminerService examinerService;

    @Autowired
    StudentService studentService;

    @Autowired
    TaskService taskService;

    @GetMapping(path = "/examiner/{examinerId}")
    public List<ExamDto> getAllExamsForExaminer(@PathVariable(value = "examinerId") int examinerId) {
        List<Exam> exams = examService.getAllExamsForExaminer(examinerId);
        List<ExamDto> examDtos = new ArrayList<>();
        for (Exam exam : exams) {
            examDtos.add(modelMapper.map(exam, ExamDto.class));
        }

        return examDtos;
    }

    @GetMapping(path = "/{id}")
    public ExamDto getExamById(@PathVariable(value = "id") int id) {
        Exam exam = examService.getExamById(id);
        return modelMapper.map(exam, ExamDto.class);
    }

    @PostMapping(path = "")
    public ExamDto uploadExam(@RequestBody ExamDto examDto) {
        Exam exam = mapExamDtoToExam(examDto);
        exam = examService.uploadExam(exam);
        return modelMapper.map(exam, ExamDto.class);
    }

    private Exam mapExamDtoToExam(ExamDto examDto) {
        Exam exam = modelMapper.map(examDto, Exam.class);
        List<Task> tasks = exam.getTasks();
        exam.setTasks(new ArrayList<>());
        for (Task task : tasks) {
            List<Step> steps = task.getSteps();
            task.setSteps(new ArrayList<>());
            for (Step step: steps) {
                task.addStep(step);
            }
            task.setExercise(null);
            task.setStudentExam(null);
            task.setStudentExercise(null);
            exam.addTask(task);
        }
        exam.setExaminer(examinerService.findById(examDto.getExaminerId()));
        return exam;
    }
}
