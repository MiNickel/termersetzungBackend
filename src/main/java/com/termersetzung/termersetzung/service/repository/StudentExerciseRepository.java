package com.termersetzung.termersetzung.service.repository;

import com.termersetzung.termersetzung.model.entities.StudentExercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentExerciseRepository extends CrudRepository<StudentExercise, Integer> {

    public List<StudentExercise> findAllByStudentId(int studentId);

    public Optional<StudentExercise> findByExerciseIdAndStudentId(int exerciseId, int studentId);

}
