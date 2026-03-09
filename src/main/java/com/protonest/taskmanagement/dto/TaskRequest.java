package com.protonest.taskmanagement.dto;

import com.protonest.taskmanagement.entity.Priority;
import com.protonest.taskmanagement.entity.TaskStatus;
import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;
}