package com.termersetzung.termersetzung.service.interfaces;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Student;

public interface StudentService {

    public Student findByFirstnameAndLastname(String firstname, String lastname);

    public Student saveStudent(String firstname, String lastname);

    public Student findById(int studentId);

    public List<Student> findByIds(String studentIds);
}