package com.termersetzung.termersetzung.service.implementation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.model.entities.Step;
import com.termersetzung.termersetzung.model.entities.StudentExam;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.StudentExamService;
import com.termersetzung.termersetzung.service.repository.ExamRepository;

import com.termersetzung.termersetzung.service.repository.TaskRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public StudentExam correctStudentExam(StudentExam studentExam) {

        List<Task> studentTasks = studentExam.getTasks();

        for (Task studentTask : studentTasks) {
            List<Step> steps = studentTask.getSteps();
            Task taskToCheck = taskRepository.findById(studentTask.getId());
            for (int i = 0; i < steps.size(); i++) {
                Step startStep = steps.get(i);
                Step targetStep = steps.get(i + 1);

                String startEquation = startStep.getStep();
                String rule = "f -> f" + startStep.getConversion();
                String targetEquation = targetStep.getStep();

                boolean isCorrect = applyTransformCheck(startEquation, rule, targetEquation);
                boolean equationExists = checkExamForEquation(taskToCheck.getSteps(), targetEquation);

                if (isCorrect) {
                    if (equationExists) {
                        studentTask.setScore(taskToCheck.getScore());
                    } else {
                        studentTask.setScore(taskToCheck.getScore() - 1);
                    }
                } else {
                    if (equationExists) {
                        studentTask.setScore(taskToCheck.getScore() - 1);
                    } else {
                        studentTask.setScore(0);
                    }
                }
            }
        }
        return studentExam;
    }

    private boolean checkExamForEquation(List<Step> taskSteps, String targetEquation) {
        for (Step step : taskSteps) {
            if (step.getStep().equals(targetEquation)) {
                return true;
            }
        }
        return false;
    }

}
