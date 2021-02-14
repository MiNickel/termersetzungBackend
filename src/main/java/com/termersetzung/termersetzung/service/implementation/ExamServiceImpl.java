package com.termersetzung.termersetzung.service.implementation;

import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.model.entities.Student;
import com.termersetzung.termersetzung.model.entities.StudentExam;
import com.termersetzung.termersetzung.service.interfaces.ExamService;
import com.termersetzung.termersetzung.service.repository.ExamRepository;
import com.termersetzung.termersetzung.service.repository.StudentExamRepository;
import com.termersetzung.termersetzung.service.repository.StudentRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

/**
 * TestServiceImpl
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentExamRepository studentExamRepository;

    @Override
    public Exam getExam(String code) {

        Exam exam = examRepository.findByCode(code).orElse(null);

        if (exam == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Die Klausur konnte nicht gefunden werden.");
        }
        return exam;

    }

    @Override
    public Exam getExamForStudent(String code, int studentId, String firstname, String lastname) {
        Exam exam = examRepository.findByCode(code).orElse(null);
        Student student = studentRepository.findByIdAndFirstnameAndLastname(studentId, firstname, lastname).orElse(null);

        if (exam == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Die Klausur konnte nicht gefunden werden.");
        } else {
            if (student != null) {
                StudentExam studentExam = studentExamRepository.findByExamIdAndStudentId(exam.getId(), student.getId()).orElse(null);
                if (studentExam != null) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sie haben die Klausur bereits absolviert.");
                }
            }
        }

        Date startDate = exam.getStartDate();
        Date endDate = exam.getEndDate();
        Date now = new Date();

        if (now.compareTo(startDate) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Die Klausur wurde noch nicht freigeschaltet.");
        } else if (now.compareTo(endDate) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Die Klausur ist nicht mehr verfügbar.");
        }

        return exam;
    }

    @Override
    public Exam uploadExam(Exam exam) {

        try {
            if ("".equals(exam.getCode()) || exam.getCode() == null) {
                String code = RandomStringUtils.random(6, true, true);
                exam.setCode(code);
            }
            exam = examRepository.save(exam);
            return exam;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }

    @Override
    public List<Exam> getAllExamsForExaminer(int examinerId) {
        try {
            return examRepository.findAllByExaminerId(examinerId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @Override
    public Exam getExamById(int id) {
        Exam exam = examRepository.findById(id).orElse(null);

        if (exam == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Die Klausur konnte nicht gefunden werden.");
        }

        return exam;
    }

}
