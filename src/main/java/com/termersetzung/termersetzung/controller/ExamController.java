package com.termersetzung.termersetzung.controller;

import com.termersetzung.termersetzung.model.dto.ExamDto;
import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.model.entities.StudentExam;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.ExamService;
import com.termersetzung.termersetzung.service.interfaces.StudentExamService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Exam getExam(@RequestParam(required = true) String code) {
        Exam exam = examService.getExam(code);
        return exam;
    }

    @RequestMapping(path = "/student", method = RequestMethod.GET)
    public ExamDto getExamForStudent(@RequestParam(required = true) String code) {
        Exam exam = examService.getExam(code);
        return examToExamDto(exam);
    }

    @RequestMapping(path = "/student", method = RequestMethod.POST)
    public void correctStudentExam(@RequestBody StudentExam studentExam) {
        studentExamService.correctStudentExam(studentExam);
    }

    @RequestMapping(path = "/studentExams", method = RequestMethod.GET)
    public List<StudentExam> getAllStudentExams() {
        List<StudentExam> studentExamList = studentExamService.getAllStudentExams();
        return studentExamList;
    }

    @RequestMapping(path = "/examiner", method = RequestMethod.GET)
    public List<Exam> getAllExamsForExaminer() {
        List<Exam> exams = examService.getAllExams();
        return exams;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Exam getExamById(@PathVariable(value = "id") int id) {
        return examService.getExamById(id);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Exam uploadExam(@RequestBody Exam exam) {
        exam = examService.uploadExam(exam);
        return exam;
    }

    private ExamDto examToExamDto(Exam exam) {
        ExamDto examDto = modelMapper.map(exam, ExamDto.class);
        List<Task> taskList = exam.getTasks();
        int counter = 0;

        for (Task task : taskList) {
            String startTerm = task.getSteps().get(0).getStep();
            examDto.getTasks().get(counter).setStartTerm(startTerm);
            counter++;
        }

        return examDto;
    }
}
