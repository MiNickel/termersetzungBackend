package com.termersetzung.termersetzung.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.termersetzung.termersetzung.model.entities.Student;
import com.termersetzung.termersetzung.service.interfaces.StudentService;
import com.termersetzung.termersetzung.service.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student findByFirstnameAndLastname(String firstname, String lastname) {
        try {
            return studentRepository.findByFirstnameAndLastname(firstname, lastname).orElse(null);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        
    }

    @Override
    public Student saveStudent(String firstname, String lastname) {
        try {
            Student student = new Student(-1, firstname, lastname, 0, null, null);
            return studentRepository.save(student);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        
    }

    @Override
    public Student findById(int studentId) {
        try {
            return studentRepository.findById(studentId).orElse(null);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        
    }

    @Override
    public List<Student> findByIds(String studentIds) {
        try {
            if (studentIds != null) {
                if (!studentIds.isEmpty()) {
                    List<Integer> ids = new ArrayList<>();
                    String[] studentIdArray = studentIds.split(",");
                    for (String studentId : studentIdArray) {
                        ids.add(Integer.parseInt(studentId));
                    }
                    return (List<Student>) studentRepository.findAllById(ids);

                }
            }
            return null;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }

}