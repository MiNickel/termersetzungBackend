package com.termersetzung.termersetzung.service.implementation;

import com.termersetzung.termersetzung.model.entities.StudentExercise;
import com.termersetzung.termersetzung.service.interfaces.StudentExerciseService;
import com.termersetzung.termersetzung.service.repository.StudentExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentExerciseServiceImpl implements StudentExerciseService {

    @Autowired
    StudentExerciseRepository studentExerciseRepository;


    @Override
    public List<StudentExercise> findAllByStudentId(int studentId) {
        try {
            return studentExerciseRepository.findAllByStudentId(studentId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Es konnten keine fertigen Übungen für den Studenten gefunden werden.");
        }
    }

    @Override
    public StudentExercise uploadStudentExercise(StudentExercise studentExercise) {
        try {
            return studentExerciseRepository.save(studentExercise);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        
    }

    @Override
    public StudentExercise findByExerciseIdAndStudentId(int exerciseId, int studentId) {
        try {
            return studentExerciseRepository.findByExerciseIdAndStudentId(exerciseId, studentId).orElse(null);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
