package com.protonest.taskmanagement.service;

import com.protonest.taskmanagement.dto.TaskRequest;
import com.protonest.taskmanagement.dto.TaskResponse;
import com.protonest.taskmanagement.entity.Task;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);

    List<TaskResponse> getMyTasks();

    TaskResponse updateTask(Long taskId, TaskRequest request);
}