package com.termersetzung.termersetzung.service.implementation;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Task;
import com.termersetzung.termersetzung.service.interfaces.TaskService;
import com.termersetzung.termersetzung.service.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TaskServiceImpl
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasksByTestId(int id) {
        List<Task> taskList = (List<Task>) taskRepository.findAllByExamId(id);
        return taskList;
    }

    @Override
    public List<Task> saveTasks(List<Task> tasks) {
        List<Task> savedTasks = (List<Task>) taskRepository.saveAll(tasks);
        return savedTasks;
    }

    
}