package com.termersetzung.termersetzung.controller;

import java.util.ArrayList;
import java.util.List;

import com.termersetzung.termersetzung.model.dto.StudentDto;
import com.termersetzung.termersetzung.model.entities.Student;
import com.termersetzung.termersetzung.service.interfaces.StudentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(path = "/")
    public List<StudentDto> getStudentsByIds(@RequestParam(required = true) String studentIds) {
         List<Student> students = studentService.findByIds(studentIds);
         List<StudentDto> studentDtos = new ArrayList<>();
         for (Student student : students) {
             studentDtos.add(modelMapper.map(student, StudentDto.class));
         }

         return studentDtos;
    }

    @GetMapping(path = "/{id}")
    public StudentDto getStudentById(@PathVariable(value = "id") int id) {
        Student student = studentService.findById(id);
        return modelMapper.map(student, StudentDto.class);
    }
    
}