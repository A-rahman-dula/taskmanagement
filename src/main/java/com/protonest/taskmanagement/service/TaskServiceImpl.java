package com.protonest.taskmanagement.service;

import com.protonest.taskmanagement.dto.TaskRequest;
import com.protonest.taskmanagement.entity.Task;
import com.protonest.taskmanagement.entity.User;
import com.protonest.taskmanagement.repository.TaskRepository;
import com.protonest.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.protonest.taskmanagement.dto.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.protonest.taskmanagement.entity.TaskStatus;



@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest request) {

        String email = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        return TaskResponse.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .status(savedTask.getStatus())
                .priority(savedTask.getPriority())
                .dueDate(savedTask.getDueDate())
                .createdAt(savedTask.getCreatedAt())
                .build();
    }

    @Override
    public Page<TaskResponse> getMyTasks(
            int page,
            int size,
            String sortBy,
            String direction,
            TaskStatus status) {

        String email = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getEmail();

        User user = userRepository.findByEmail(email).orElseThrow();

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Task> taskPage;

        if (status != null) {
            taskPage = taskRepository.findByUserIdAndStatus(user.getId(), status, pageable);
        } else {
            taskPage = taskRepository.findByUserId(user.getId(), pageable);
        }

        return taskPage.map(task ->
                TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .status(task.getStatus())
                        .priority(task.getPriority())
                        .dueDate(task.getDueDate())
                        .createdAt(task.getCreatedAt())
                        .build()
        );
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest request) {

        String email = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // SECURITY CHECK
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to update this task");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        Task updatedTask = taskRepository.save(task);

        return TaskResponse.builder()
                .id(updatedTask.getId())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .status(updatedTask.getStatus())
                .priority(updatedTask.getPriority())
                .dueDate(updatedTask.getDueDate())
                .createdAt(updatedTask.getCreatedAt())
                .build();
    }

    @Override
    public void deleteTask(Long taskId) {

        String email = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Ownership validation
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this task");
        }

        taskRepository.delete(task);
    }

    @Override
    public Page<TaskResponse> getAllTasks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Task> taskPage = taskRepository.findAll(pageable);

        return taskPage.map(task ->
                TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .status(task.getStatus())
                        .priority(task.getPriority())
                        .dueDate(task.getDueDate())
                        .createdAt(task.getCreatedAt())
                        .build()
        );
    }
}