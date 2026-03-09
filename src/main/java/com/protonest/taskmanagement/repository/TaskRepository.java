package com.protonest.taskmanagement.repository;

import com.protonest.taskmanagement.entity.Task;
import com.protonest.taskmanagement.entity.TaskStatus;
import com.protonest.taskmanagement.entity.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{

    Page<Task> findByUserId(Long userId, Pageable pageable);

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Page<Task> findByPriority(Priority priority, Pageable pageable);



}
