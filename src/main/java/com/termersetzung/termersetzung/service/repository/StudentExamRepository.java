package com.termersetzung.termersetzung.service.repository;

import java.util.List;
import java.util.Optional;

import com.termersetzung.termersetzung.model.entities.StudentExam;
import org.springframework.data.repository.CrudRepository;

public interface StudentExamRepository extends CrudRepository<StudentExam, Integer> {

    public List<StudentExam> findAllByExamId(int examId);

    public Optional<StudentExam> findByExamIdAndStudentId(int examId, int studentId);
}
