package com.protonest.taskmanagement.controller;

import com.protonest.taskmanagement.dto.TaskRequest;
import com.protonest.taskmanagement.dto.TaskResponse;
import com.protonest.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getMyTasks() {
        return taskService.getMyTasks();
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