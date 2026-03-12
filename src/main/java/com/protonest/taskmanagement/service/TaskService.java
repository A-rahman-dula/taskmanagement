package com.protonest.taskmanagement.service;

import com.protonest.taskmanagement.dto.TaskRequest;
import com.protonest.taskmanagement.dto.TaskResponse;
import com.protonest.taskmanagement.entity.Task;
import org.springframework.data.domain.Page;
import com.protonest.taskmanagement.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);

    Page<TaskResponse> getMyTasks(
            int page,
            int size,
            String sortBy,
            String direction,
            TaskStatus status);

    TaskResponse updateTask(Long taskId, TaskRequest request);

    void deleteTask(Long taskId);

    Page<TaskResponse> getAllTasks(int page, int size);
}