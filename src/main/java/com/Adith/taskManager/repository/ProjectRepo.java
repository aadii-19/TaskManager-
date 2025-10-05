package com.Adith.taskManager.repository;

import com.Adith.taskManager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
    Project findProjectsByIdIs(long id);      //---> This is not right because this checks whether the id matches with projectId but, we want to check with userId;
    List<Project> findByOwnerId(long ownerId);
}
