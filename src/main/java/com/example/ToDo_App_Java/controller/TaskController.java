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
import java.util.Optional;

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
        boolean valid = jwtService.validateToken(token);

        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

        String email = jwtService.extractEmail(token);


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
        boolean valid = jwtService.validateToken(token);

        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }

        String email = jwtService.extractEmail(token);

        User user = userService.getEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
        }

        task.setUser(user);
        Task savedTask = taskService.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task task, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        boolean valid = jwtService.validateToken(token);

        if(!valid){
            return ResponseEntity.notFound().build();
        }

        Long userId = jwtService.extractUserId(token);

        Optional<Task> optionalTask = taskService.getTaskByIdAndUserId(id, userId);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task existingTask = optionalTask.get();
        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.isCompleted());

        Task saved = taskService.save(existingTask); // or taskRepository.save(existingTask)
        return ResponseEntity.ok(saved);

    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        boolean valid = jwtService.validateToken(token);

        if(!valid){
            return ResponseEntity.noContent().build();
        }
        Long userId = jwtService.extractUserId(token);

        // Check if the task exists for the user
        Optional<Task> optionalTask = taskService.getTaskByIdAndUserId(id, userId);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if task is not found for the user
        }

        // Delete the task using its ID and the user ID
        taskService.deleteTask(id, userId);  // Pass taskId and userId to the delete method
        return ResponseEntity.noContent().build();  // Return 204 No Content if task is successfully deleted

    }

    @PostMapping("signup")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


}
