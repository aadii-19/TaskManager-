package com.Adith.taskManager.entity;


import com.Adith.taskManager.enums.Priority;
import com.Adith.taskManager.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    private String description;

    @Enumerated (EnumType.STRING)
    private Status status;

    @Enumerated (EnumType.STRING)
    private Priority priority;

    private LocalDate deadLine;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void isCreated(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void isUpdated(){
        this.updatedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)
    private User assignedTo;
}
