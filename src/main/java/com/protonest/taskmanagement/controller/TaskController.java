package com.protonest.taskmanagement.controller;

import com.protonest.taskmanagement.dto.TaskRequest;
import com.protonest.taskmanagement.dto.TaskResponse;
import com.protonest.taskmanagement.entity.TaskStatus;
import com.protonest.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public Page<TaskResponse> getMyTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) TaskStatus status) {

        return taskService.getMyTasks(page, size, sortBy, direction, status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public Page<TaskResponse> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return taskService.getAllTasks(page, size);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}