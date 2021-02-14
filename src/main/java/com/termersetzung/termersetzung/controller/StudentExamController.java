package com.termersetzung.termersetzung.controller;

import java.util.ArrayList;
import java.util.List;

import com.termersetzung.termersetzung.model.dto.StepDto;
import com.termersetzung.termersetzung.model.dto.StudentExamDto;
import com.termersetzung.termersetzung.model.dto.TaskDto;
import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.model.entities.Step;
import com.termersetzung.termersetzung.model.entities.StudentExam;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.ExamService;
import com.termersetzung.termersetzung.service.interfaces.StudentExamService;
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
@RequestMapping(value = "/studentexam")
public class StudentExamController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StudentExamService studentExamService;

    @Autowired
    ExamService examService;

    @Autowired
    StudentService studentService;

    @GetMapping(path = "/{code}/{studentId}/{firstname}/{lastname}")
    public StudentExamDto getExamForStudent(@PathVariable(value = "code") String code,
            @PathVariable(value = "studentId") int studentId, @PathVariable(value = "firstname") String firstname,
            @PathVariable(value = "lastname") String lastname) {
        Exam exam = examService.getExamForStudent(code, studentId, firstname, lastname);
        return mapExamToStudentExamDto(exam);
    }

    @PostMapping(path = "/")
    public void correctStudentExam(@RequestBody StudentExamDto studentExamDto) {
        StudentExam studentExam = mapStudentExamDtoToStudentExam(studentExamDto);
        studentExamService.correctStudentExam(studentExam);
    }

    @GetMapping(path = "/all")
    public List<StudentExamDto> getAllStudentExams() {
        List<StudentExam> studentExams = studentExamService.getAllStudentExams();
        List<StudentExamDto> studentExamDtos = new ArrayList<>();
        for (StudentExam studentExam : studentExams) {
            studentExamDtos.add(modelMapper.map(studentExam, StudentExamDto.class));
        }
        return studentExamDtos;
    }

    @GetMapping(path = "/all/{examId}")
    public List<StudentExamDto> getAllStudentExamsWithExamId(@PathVariable(value = "examId") int examId) {
        List<StudentExam> studentExams = studentExamService.getAllStudentExamsWithExamId(examId);
        List<StudentExamDto> studentExamDtos = new ArrayList<>();
        for (StudentExam studentExam : studentExams) {
            studentExamDtos.add(modelMapper.map(studentExam, StudentExamDto.class));
        }

        return studentExamDtos;
    }

    @GetMapping(path = "/{id}")
    public StudentExamDto getStudentExamById(@PathVariable(value = "id") int id) {
        StudentExam studentExam = studentExamService.getStudentExamById(id);
        return modelMapper.map(studentExam, StudentExamDto.class);
    }

    private StudentExamDto mapExamToStudentExamDto(Exam exam) {
        StudentExamDto studentExamDto = modelMapper.map(exam, StudentExamDto.class);
        studentExamDto.setId(-1);
        studentExamDto.setStudentId(-1);
        studentExamDto.setTasks(new ArrayList<>());
        List<Task> taskList = exam.getTasks();

        for (Task task : taskList) {
            TaskDto taskDto = modelMapper.map(task, TaskDto.class);
            taskDto.setId(-1);
            taskDto.setExamId(0);
            taskDto.setExerciseId(0);
            taskDto.setStudentExerciseId(0);
            taskDto.setStudentExamId(-1);
            Step step = task.getSteps().get(0);
            taskDto.setSteps(new ArrayList<>());
            StepDto stepDto = modelMapper.map(step, StepDto.class);
            stepDto.setConversion("");
            stepDto.setScore(0);
            stepDto.setId(-1);
            stepDto.setTaskId(-1);
            taskDto.getSteps().add(stepDto);
            studentExamDto.getTasks().add(taskDto);
        }

        return studentExamDto;
    }

    private StudentExam mapStudentExamDtoToStudentExam(StudentExamDto studentExamDto) {
        StudentExam studentExam = modelMapper.map(studentExamDto, StudentExam.class);
        List<Task> tasks = studentExam.getTasks();
        studentExam.setTasks(new ArrayList<>());
        for (Task task : tasks) {
            List<Step> steps = task.getSteps();
            task.setSteps(new ArrayList<>());
            for (Step step : steps) {
                task.addStep(step);
            }
            task.setExercise(null);
            task.setExam(null);
            task.setStudentExercise(null);
            task.setScore(0);
            studentExam.addTask(task);
        }
        studentExam.setExam(examService.getExamById(studentExamDto.getExamId()));
        studentExam.setStudent(studentService.findById(studentExamDto.getStudentId()));
        return studentExam;
    }
}