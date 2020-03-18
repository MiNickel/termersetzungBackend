package com.termersetzung.termersetzung.service.implementation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.termersetzung.termersetzung.model.entities.Exam;
import com.termersetzung.termersetzung.model.entities.StudentExam;
import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.StudentExamService;
import com.termersetzung.termersetzung.service.repository.ExamRepository;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentExamServiceImpl
 */
@Service
public class StudentExamServiceImpl implements StudentExamService {

    @Autowired
    ExamRepository examRepository;

    @Override
    public StudentExam correctStudentExam(StudentExam studentExam) {

        try {
            boolean isCorrect = applyTransformCheck("blib", "blub", "blab");

            if (isCorrect) {
                
            }
        } catch (Exception e) {

        }

        List<Task> studentTasks = studentExam.getTasks();
        Exam exam = studentExam.getExam();
        List<Task> examTasks = exam.getTasks();

        for (Task studentTask : studentTasks) {

        }

        return null;
    }

    private boolean applyTransformCheck(String startEquation, String rule, String targetEquation) {
        boolean result = false; 
        try {
            String url = "http://localhost:8080/MathParserDev/equation/apply_transform_check";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("startEquation", "x-2=-1");
            jsonObject.put("rule", "f -> f+2");
            jsonObject.put("targetEquation", "x=1");

            String jsonToString = jsonObject.toString();

            StringEntity stringEntity = new StringEntity(jsonToString);
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
            }

            String resultString = stringBuffer.toString();

            JSONObject resultObject = new JSONObject(resultString);
            String code = resultObject.get("code").toString();

            if (code.equals("0")) {
                result = true;
            }

        } catch (Exception ex) {

        }

        return result;

    }

}