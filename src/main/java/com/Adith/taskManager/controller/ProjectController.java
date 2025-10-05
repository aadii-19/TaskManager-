package com.Adith.taskManager.controller;


import com.Adith.taskManager.entity.Project;
import com.Adith.taskManager.entity.User;
import com.Adith.taskManager.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projService;

    public ProjectController(ProjectService projService){
        this.projService = projService;
    }

    @PostMapping("/users/{userId}/projects")
    public ResponseEntity<?> addProject(@PathVariable long userId,@RequestBody Project project){
        Project proj = projService.addProject(userId,project);
        System.out.println("Project added Successfully ");
        return new ResponseEntity<>(proj, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/projects")   //the project in the end is not technically required
                                               // but get data of user but of what so project is for understanding and clarity purpose.
    public ResponseEntity<List<Project>> getProject(@PathVariable int userId){
        try{
            List<Project> projectList = projService.getProject(userId);
            return new ResponseEntity<>(projectList,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Not found "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    // getting Project by id
    @GetMapping("/users/{userId}/projects/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable long userId, @PathVariable long projectId){
        try {
            Project project = projService.getProjectById(userId, projectId);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unauthorized "+e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    // update project by id
    @PutMapping("/users/{userId}/projects/{projectId}")
    public ResponseEntity<?> updateProjectById(@PathVariable long userId, @PathVariable long projectId, @RequestBody Project proj){
        try{
            Project project = projService.updateProjectById(userId, projectId, proj);
            System.out.println("Updated Successfully! ");
            Map<String, String> body = new HashMap<>();
            body.put("message","Project Updated Successfully");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            Map<String,String> body = new HashMap<>();
            body.put("message","Unauthorized Access ");
            return new ResponseEntity<>(body,HttpStatus.FORBIDDEN);
        }
    }

    //Deleting the project
    @DeleteMapping("/users/{userId}/projects/{projectId}")
    public ResponseEntity<?> deleteById(@PathVariable long userId, @PathVariable long projectId){
        try{
            projService.deleteByUserId(userId,projectId);
            Map<String, String> body  = new HashMap<>();
            body.put("message","project deleted successfully");
            return new ResponseEntity<>(body,HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> body  = new HashMap<>();
            body.put("message","Encountered some error!");
            return new ResponseEntity<>(body,HttpStatus.OK);
        }
    }
}
