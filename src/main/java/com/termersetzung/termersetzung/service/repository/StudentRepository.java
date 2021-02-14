package com.termersetzung.termersetzung.service.repository;

import java.util.Optional;

import com.termersetzung.termersetzung.model.entities.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    public Optional<Student> findByFirstnameAndLastname(String firstname, String lastname);

    public Optional<Student> findByIdAndFirstnameAndLastname(int id, String firstname, String lastname);
}