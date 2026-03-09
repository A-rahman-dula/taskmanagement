package com.protonest.taskmanagement.dto;

import com.protonest.taskmanagement.entity.Priority;
import com.protonest.taskmanagement.entity.TaskStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
}