package com.krish.taskapi.restfultaskapi.service;

import java.util.ArrayList;
import java.util.List;

import com.krish.taskapi.restfultaskapi.models.Task;

import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class TaskService 
{
    private static List<Task> tasks = new ArrayList<>();

    // ...Retrieve all Tasks
    public List<Task> getAllTasks() { 
        return tasks; 
    }

    // ...Retrieve a Task by ID
    public Task getTaskById(int id) { 
        for (Task task : tasks) { 
            if (task.getId() == id) { 
                return task;
            } 
        } 
        return null; 
    }

    // ...Create a Task
    public Task createTask(Task task) { 
        tasks.add(task); 
        return task; 
    }
    
    // ...Update a Task by ID
    public void updateTask(Task task) {
        tasks.set(tasks.indexOf(task), task);
    }
    
    // ...Delete a Task by ID
    public void deleteTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
            }
        }
    }

}