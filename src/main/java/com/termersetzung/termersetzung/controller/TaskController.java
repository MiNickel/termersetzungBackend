package com.termersetzung.termersetzung.controller;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TaskController
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Task> getTasksByTestId(@RequestParam(required = true) int testId) {
        return (taskService.getAllTasksByTestId(testId));
    }

}