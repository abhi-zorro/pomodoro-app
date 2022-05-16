package com.zemoso.pomodoroapp.dao;

import com.zemoso.pomodoroapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("select t from Task t where t.user.id = ?1")
    List<Task> findByUserId(int userId);

    @Transactional
    @Modifying
    @Query("delete Task t where t.id = ?1")
    void deleteByTaskId(int taskId);

    @Transactional
    @Modifying
    @Query("delete Task t where t.user.id = ?1")
    void deleteByUserId(int userId);
}
