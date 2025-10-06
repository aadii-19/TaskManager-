package com.Adith.taskManager.controller;

import com.Adith.taskManager.entity.User;
import com.Adith.taskManager.enums.Role;
import com.Adith.taskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")               // Set the base url
public class UserController {
//    @Autowired
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    //  -------------------------- REGISTER USER --------------------------------
    //  -------------------------------------------------------------------------
    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            User user1 = service.registerUser(user);
            System.out.println("Created Successfully "+user.getFirstName());
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  -------------------------- GET USER -----------------------------------
    //  -----------------------------------------------------------------------
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestBody User user){
        if(service.isAdmin(user)){
            try{
                List<User> userList = service.getUsers();
                return new ResponseEntity<>(userList, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Unable to fetch :( "+e.getMessage(),HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>("Unauthorised ",HttpStatus.OK);
        }
    }

    //  -------------------------- DELETE USER --------------------------------
    //  -----------------------------------------------------------------------
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId){
        try{
            service.deleteUserById(userId);
            Map<String, String> body = new HashMap<>();
            body.put("message","User Deleted Successfully!");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("message","Faced some Error "+e.getMessage());
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

}
