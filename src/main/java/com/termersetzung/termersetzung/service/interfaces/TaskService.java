package com.termersetzung.termersetzung.service.interfaces;

import java.util.List;

import com.termersetzung.termersetzung.model.entities.Task;

/**
 * TaskService
 */
public interface TaskService {

    public List<Task> getAllTasksByTestId(int id);

    public List<Task> saveTasks(List<Task> tasks);
}