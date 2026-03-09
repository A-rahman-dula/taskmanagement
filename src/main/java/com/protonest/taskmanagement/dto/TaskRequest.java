package com.protonest.taskmanagement.dto;

import com.protonest.taskmanagement.entity.Priority;
import com.protonest.taskmanagement.entity.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {

    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
}