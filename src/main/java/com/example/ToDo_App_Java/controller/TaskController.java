package com.example.todo_app_java.controller;

import com.example.todo_app_java.model.Task;
import com.example.todo_app_java.model.User;
import com.example.todo_app_java.service.TaskService;
import com.example.todo_app_java.service.UserService;
import com.example.todo_app_java.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final JwtService jwtService;


    public TaskController(TaskService taskService , UserService userService , JwtService jwtService) {
        this.taskService = taskService;
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @GetMapping("tasks")
    public ResponseEntity<?> getAllTasks(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);

        User user = userService.getEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
        }

        List<Task> tasks = taskService.findByUser(user);
        return ResponseEntity.ok(tasks);
    }


    @PostMapping("task")
    public ResponseEntity<?> createTask(@RequestBody Task task, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);

        User user = userService.getEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
        }

        task.setUser(user);
        Task savedTask = taskService.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updated = taskService.updateTask(id, task);
        if (updated != null) return ResponseEntity.ok(updated);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("signup")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


}
