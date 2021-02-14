package com.termersetzung.termersetzung.controller;

import com.termersetzung.termersetzung.model.dto.CredentialsDto;
import com.termersetzung.termersetzung.model.entities.Examiner;
import com.termersetzung.termersetzung.model.entities.Student;
import com.termersetzung.termersetzung.service.interfaces.AuthenticationService;
import com.termersetzung.termersetzung.service.interfaces.ExaminerService;
import com.termersetzung.termersetzung.service.interfaces.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.opensagres.xdocreport.document.json.JSONObject;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ExaminerService examinerService;

    @Autowired
    StudentService studentService;

    @PostMapping(path = "")
    public String login(@RequestBody CredentialsDto credentials) {
        JSONObject json = authenticationService.authenticate(credentials.getUsername(), credentials.getPassword());

        String fullname = (String) json.get("fullname");
        String type = (String) json.get("type");
        String[] splitName = fullname.split(" ");

        if (type.equals("professors")) {
            Examiner examiner = examinerService.findByFirstnameAndLastname(splitName[0], splitName[1]);
            if (examiner == null) {
                Examiner savedExaminer = examinerService.saveExaminer(splitName[0], splitName[1]);
                json.put("examinerId", savedExaminer.getId());
            } else {
                json.put("examinerId", examiner.getId());
            }
        } else if (type.equals("students")) {
            Student student = studentService.findByFirstnameAndLastname(splitName[0], splitName[1]);
            if (student == null) {
                Student savedStudent = studentService.saveStudent(splitName[0], splitName[1]);
                json.put("studentId", savedStudent.getId());
            } else {
                json.put("studentId", student.getId());
            }
        }

        return json.toString();
    }

}