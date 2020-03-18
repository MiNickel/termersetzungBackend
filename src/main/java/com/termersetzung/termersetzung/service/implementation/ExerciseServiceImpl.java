package com.termersetzung.termersetzung.service.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.termersetzung.termersetzung.model.dto.StepCheckDto;
import com.termersetzung.termersetzung.model.entities.Exercise;
import com.termersetzung.termersetzung.service.interfaces.ExerciseService;
import com.termersetzung.termersetzung.service.repository.ExerciseRepository;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * ExerciseServiceImpl
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Override
    public Exercise getExerciseById(int id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);

        if (exercise == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Die Klausur konnte nicht gefunden werden.");
        }
        return exercise;
    }

    @Override
    public List<Exercise> getAllExercises() {
        try {
            List<Exercise> exercises = (List<Exercise>) exerciseRepository.findAll();
            return exercises;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }

    @Override
    public Exercise uploadExercise(Exercise exercise) {
        try {
            exercise = exerciseRepository.save(exercise);
            return exercise;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }

    @Override
    public List<StepCheckDto> checkSteps(List<StepCheckDto> stepList) {
        stepList.get(0);
        for (int i = 0; i < stepList.size(); i++) {
            StepCheckDto startEquation = stepList.get(i);
            String rule =  "f -> f" + startEquation.getConversion();
            String targetEquation = stepList.get(i+1).getStep();
            boolean isCorrect = applyTransformCheck(startEquation.getStep(), rule, targetEquation);
            
            stepList.get(i).setCorrect(isCorrect);
        }
        return stepList;
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