package com.termersetzung.termersetzung.service.implementation;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.model.entities.Step;
import com.termersetzung.termersetzung.model.entities.StudentExam;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.StudentExamService;
import com.termersetzung.termersetzung.service.repository.ExamRepository;

import com.termersetzung.termersetzung.service.repository.StudentExamRepository;
import com.termersetzung.termersetzung.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.termersetzung.termersetzung.service.implementation.SharedMethodsImpl.applyTransformCheck;

/**
 * StudentExamServiceImpl
 */
@Service
public class StudentExamServiceImpl implements StudentExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StudentExamRepository studentExamRepository;

    @Override
    public StudentExam correctStudentExam(StudentExam studentExam) {
        List<Task> studentTasks = studentExam.getTasks();
        Exam exam = studentExam.getExam();
        for (int i = 0; i < studentTasks.size(); i++) {
            Task task = studentTasks.get(i);
            List<Step> steps = studentTasks.get(i).getSteps();
            Task taskToCheck = exam.getTasks().get(i);
            int stepSize = exam.getTasks().get(i).getSteps().size();
            String solution = exam.getTasks().get(i).getSteps().get(stepSize - 1).getStep();
            for (int j = 0; j < steps.size() - 1; j++) {
                Step startStep = steps.get(j);
                Step targetStep = steps.get(j + 1);

                String startEquation = startStep.getStep();
                String rule = "f -> f" + startStep.getConversion();
                String targetEquation = targetStep.getStep();

                boolean isCorrect = applyTransformCheck(startEquation, rule, targetEquation);
                int equationExistsScore = checkExamForEquation(taskToCheck.getSteps(), targetEquation);

                setStepScore(targetStep, equationExistsScore, isCorrect);

                if (targetEquation.equals(solution)) {
                    task.setScore(taskToCheck.getScore());
                }
            }

            if (task.getScore() == 0) {
                int completeScore = 0;
                for (int k = 0; k < steps.size(); k++) {
                    completeScore += steps.get(k).getScore();
                }
    
                if (completeScore > taskToCheck.getScore()) {
                    task.setScore(taskToCheck.getScore());
                } else {
                    task.setScore(completeScore);
                }
            }
            
        }
        studentExamRepository.save(studentExam);
        return studentExam;
    }

    @Override
    public List<StudentExam> getAllStudentExams() {
        return (List<StudentExam>) studentExamRepository.findAll();
    }

    private int checkExamForEquation(List<Step> taskSteps, String targetEquation) {
        for (Step step : taskSteps) {
            if (step.getStep().equals(targetEquation)) {
                return step.getScore();
            }
        }
        return -1;
    }

    private void setStepScore(Step targetStep, int score, boolean isCorrect) {
        if (isCorrect) {
            if (score >= 0) {
                targetStep.setScore(score);
            } else {
                targetStep.setScore(1);
            }
        } else {
            if (score >= 0) {
                targetStep.setScore(score - 1);
            } else {
                targetStep.setScore(0);
            }
        }
    }

    @Override
    public List<StudentExam> getAllStudentExamsWithExamId(int examId) {
        try {
            return studentExamRepository.findAllByExamId(examId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }

    @Override
    public StudentExam getStudentExamById(int studentExamId) {
        try {
            return studentExamRepository.findById(studentExamId).orElse(null);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
