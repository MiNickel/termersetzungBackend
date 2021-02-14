package com.termersetzung.termersetzung.service.interfaces;

import com.termersetzung.termersetzung.model.entities.StudentExercise;

import java.util.List;

public interface StudentExerciseService {

    public List<StudentExercise> findAllByStudentId(int studentId);

    public StudentExercise uploadStudentExercise(StudentExercise studentExercise);

    public StudentExercise findByExerciseIdAndStudentId(int exerciseId, int studentId);
}
