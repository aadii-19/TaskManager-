package com.Adith.taskManager.service;

import com.Adith.taskManager.entity.Project;
import com.Adith.taskManager.entity.Task;
import com.Adith.taskManager.entity.User;
import com.Adith.taskManager.repository.ProjectRepo;
import com.Adith.taskManager.repository.TaskRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskService(TaskRepo taskRepo, ProjectService projectService, UserService userService){
        this.taskRepo = taskRepo;
        this.projectService = projectService;
        this.userService = userService;
    }

    public void addTask(long userId, long projectId, Task task) {
        Project proj = projectService.getProjectById(userId,projectId);
        User owner = userService.getUserById(userId);
        if(proj!=null && owner!=null){
//            task.setId(userId);    // Never set this, it is automatically assigned to the task
            task.setAssignedTo(owner);
            task.setProject(proj);
            taskRepo.save(task);
        }else{
            if(proj==null){
                throw new RuntimeException("Project Not found");
            }
            else{
                throw new RuntimeException("Owner doesn't exist");
            }
        }
    }
}
