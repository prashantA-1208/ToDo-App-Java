package com.example.todo_app_java.repository;

import com.example.todo_app_java.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.todo_app_java.model.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    Optional<Task> findByTaskIdAndUser_UserId(Long taskId, Long userId);


    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.taskId = :taskId AND t.user.userId = :userId")
    void deleteByTaskIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);
}
