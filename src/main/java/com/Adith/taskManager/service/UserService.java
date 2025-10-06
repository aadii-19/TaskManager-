package com.Adith.taskManager.service;

import com.Adith.taskManager.entity.User;
import com.Adith.taskManager.enums.Role;
import com.Adith.taskManager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }
    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public Boolean isAdmin(User user){
        return user.getRole() == Role.ADMIN;
    }

    public User getUserById(long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public void deleteUserById(long userId) {
        userRepo.deleteById(userId);
    }
}
