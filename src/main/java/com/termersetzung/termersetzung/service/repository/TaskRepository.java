package com.termersetzung.termersetzung.service.repository;

import com.termersetzung.termersetzung.model.entities.Task;

import org.springframework.data.repository.CrudRepository;

/**
 * TaskRepository
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {

    Iterable<Task> findAllByExamId(int examId);

    Iterable<Task> findAllByExerciseId(int exerciseId);
}