package com.Adith.taskManager.service;

import com.Adith.taskManager.entity.Project;
import com.Adith.taskManager.entity.User;
import com.Adith.taskManager.repository.ProjectRepo;
import com.Adith.taskManager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;
    // So we need an instance of UserRepo to lookUp users
    private final UserRepository userRepo;
    public ProjectService(ProjectRepo projectRepo, UserRepository userRepo){
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }
    public Project addProject(long id,Project project) {
        // Find the user (owner) by ID.
        //Assign that user as the project’s owner.
        //Save the project.
        User owner = userRepo.findById(id)      // Since it was an optional we used orElseThrow method
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        // Now set the owner to the project
        project.setOwner(owner);
        return projectRepo.save(project);
    }

    public List<Project> getProject(long userId) {
        return projectRepo.findByOwnerId(userId);
    }

    public Project getProjectById(long userId, long projectId) {
        Project proj = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        if (proj.getOwner() != null && proj.getOwner().getId() == userId) {
            return proj;
        } else {
            throw new RuntimeException("Unauthorized access");
        }
    }

    public Project updateProjectById(long userId, long projectId, Project proj) {
        Project userProject = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        if(userProject.getOwner()!=null && userProject.getOwner().getId()==(userId)){
            userProject.setName(proj.getName());
            userProject.setDescription(proj.getDescription());
            return projectRepo.save(userProject);
        }else{
            throw new RuntimeException("Unauthorized access");
        }
    }

    public void deleteByUserId(long userId, long projectId) {
        Project proj = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Error"));
        if(proj.getOwner()!=null && proj.getOwner().getId()==userId){
            projectRepo.deleteById(projectId);
        }else{
            throw new RuntimeException("Unauthorized Access");
        }
    }


}
