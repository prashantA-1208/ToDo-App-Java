package com.example.todo_app_java.service;


import com.example.todo_app_java.model.Task;
import com.example.todo_app_java.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.example.todo_app_java.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {


    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        return taskRepository.findById(taskId).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(task);
        }).orElse(null);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }
    public List<Task> findByUser(User user){
        return taskRepository.findByUser(user);

        // convert to dto
    }
}
