package com.Adith.taskManager.controller;

import com.Adith.taskManager.entity.Task;
import com.Adith.taskManager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/{userId}/projects/{projectId}")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    //  -------------------------- REGISTER TASK --------------------------------
    //  -------------------------------------------------------------------------
    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@PathVariable long userId, @PathVariable long projectId, @RequestBody Task task){
        try{
            taskService.addTask(userId,projectId,task);
            Map<String, String> body = new HashMap<>();
            body.put("message","task added successfully! ");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("message","task adding unsuccessful");
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    //  --------------------- UPDATE TASK BY TASK_ID ----------------------------
    //  -------------------------------------------------------------------------
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable long userId, @PathVariable long projectId,@PathVariable long taskId, @RequestBody Task task){
        try{
            taskService.updateTask(userId,projectId,taskId,task);
            Map<String, String> body = new HashMap<>();
            body.put("message","task updated successfully! ");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("message","task update unsuccessfully! ");
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }
}
