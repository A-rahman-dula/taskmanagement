package com.protonest.taskmanagement.service;

import com.protonest.taskmanagement.dto.TaskRequest;
import com.protonest.taskmanagement.dto.TaskResponse;
import com.protonest.taskmanagement.entity.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);

    Page<TaskResponse> getMyTasks(int page, int size, String sortBy, String direction);

    TaskResponse updateTask(Long taskId, TaskRequest request);

    void deleteTask(Long taskId);
}