package com.Adith.taskManager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.Adith.taskManager.enums.Role;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor         //this is needed for JPA to create objects first
@Builder
@Table(name = "users")     //by default, it is className but, it can be changed.
public class User {
    @Id //marked as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private long id;


    @Column (nullable = false)  // This column cannot be null in DB
    private String firstName;
    private String lastName;


    @Email
    @NotBlank    // Shouldn't be blank
    @Column (nullable = false, unique = true)  //Shouldn't be null but also should be unique.
    private String email;


    @Column (nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)      // Store the enum only as String
    @Column(nullable = false)
    private Role role;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


//    @OneToMany → One parent entity has multiple children.
//    mappedBy → Relationship is already defined on the other side (foreign key lives there).
//    cascade = CascadeType.ALL → Any operation on parent cascades to children.
//    orphanRemoval = true → Removes child from DB when it’s no longer in parent’s collection.

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // It is a JackSon annotation it basically tells jackson : When converting this object to JSON, SKIP this field!
    // Since this is a bidirectional relationship, if we won't
    private List<Project> projects;

    @PrePersist   //Executes before inserting a new entity
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Task> tasks;
}
