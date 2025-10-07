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

    //  --------------------- ADDING TASK BY PROJECT_ID -------------------------
    //  -------------------------------------------------------------------------
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

    //  --------------------- UPDATE TASK BY TASK_ID ----------------------------
    //  -------------------------------------------------------------------------
    public void updateTask(long userId, long projectId, long taskId, Task task) {
        /*
        1. Get the prev task using taskId
        2. Check if the projId is authentic
        3. Check the authenticity of the userId
        4. If all true, then update old with new task
         */
        Task prevTask = taskRepo.findById(taskId).
                orElseThrow(() -> new RuntimeException("Task Not Found"));
        if(prevTask.getProject().getId()!=projectId){
            throw new RuntimeException("Task doesn't belong to this project");
        }
        if(prevTask.getProject().getOwner().getId()!=userId){
            throw new RuntimeException("You don't have permission to modify this!");
        }
        prevTask.setTitle(task.getTitle());
        prevTask.setDescription(task.getDescription());
        prevTask.setPriority(task.getPriority());
        prevTask.setStatus(task.getStatus());
        prevTask.setDeadLine(task.getDeadLine());
    }
}
