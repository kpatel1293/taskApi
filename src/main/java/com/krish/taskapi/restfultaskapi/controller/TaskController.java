package com.krish.taskapi.restfultaskapi.controller;

import java.util.List;

import com.krish.taskapi.restfultaskapi.models.Task;
import com.krish.taskapi.restfultaskapi.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * TaskController
 */
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    // GET: Retrieve all Tasks - /
    @GetMapping(value="/")
    public List<Task> getAllTasks() {
        System.out.println("Got All Tasks!");
        return taskService.getAllTasks();
    }
    
    // GET: Retrieve a Task by ID - /{id}
    @GetMapping(value="/{id}")
    public Task getTaskById(@PathVariable int id) {
        System.out.println("Looking for tasks with id: " + id);

        return taskService.getTaskById(id);
    }

    // POST: Create a Task - /
    @PostMapping(value="/")
    public ResponseEntity<Void> createTask(@RequestBody Task newTask, UriComponentsBuilder builder) {
        System.out.println("Creating User!");
        
        Task task = taskService.createTask(newTask);

        if (task == null) {
            return ResponseEntity.noContent().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    

    // PUT: Update a Task by ID - /{id}
    @PutMapping(value="/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        System.out.println("Updating User!");

        Task currentTask = taskService.getTaskById(id);

        if (currentTask == null) { return ResponseEntity.noContent().build(); }

        currentTask.setId(task.getId());
        currentTask.setTitle(task.getTitle());
        currentTask.setDescription(task.getDescription());
        currentTask.setCompleted(task.getCompleted());

        taskService.updateTask(currentTask);

        return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
    }

    // DELETE: Delete a Task by ID - /{id}
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Task> deleteTask(@PathVariable int id) {
        System.out.println("Deleting User!");

        Task task = taskService.getTaskById(id);
    
        if (task == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        
        taskService.deleteTask(id);
        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
    }
}