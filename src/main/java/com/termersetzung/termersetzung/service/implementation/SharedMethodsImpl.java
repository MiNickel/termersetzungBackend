package com.termersetzung.termersetzung.service.implementation;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SharedMethodsImpl {

    private SharedMethodsImpl() {

    }

    public static boolean applyTransformCheck(String startEquation, String rule, String targetEquation) {
        boolean result = false;
        try {
            String url = "http://localhost:8080/MathParserDev/equation/apply_transform_check";
            HttpResponse response;
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpPost httpPost = new HttpPost(url);

                httpPost.setHeader("Content-Type", "application/json");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("startEquation", startEquation);
                jsonObject.put("rule", rule);
                jsonObject.put("targetEquation", targetEquation);

                String jsonToString = jsonObject.toString();

                StringEntity stringEntity = new StringEntity(jsonToString);
                httpPost.setEntity(stringEntity);

                response = httpClient.execute(httpPost);
            }

            StringBuilder stringBuffer;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {

                stringBuffer = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    stringBuffer.append(line);
                }
            }

            String resultString = stringBuffer.toString();

            JSONObject resultObject = new JSONObject(resultString);
            String code = resultObject.get("code").toString();

            if (code.equals("0")) {
                result = true;
            }

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

        return result;
    }
}
