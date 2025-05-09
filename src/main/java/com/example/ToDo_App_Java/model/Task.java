package com.example.todo_app_java.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;
    private boolean completed;

    // Getters and Setters
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long TaskID) { this.taskId = this.taskId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
