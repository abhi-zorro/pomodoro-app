package com.zemoso.pomodoroapp.dao;

import com.zemoso.pomodoroapp.entity.Pomodoro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PomodoroRepository extends JpaRepository<Pomodoro, Integer> {
    @Query("select p from Pomodoro p where p.task.id = ?1")
    List<Pomodoro> findByTaskId(int taskId);

    @Transactional
    @Modifying
    @Query("delete Pomodoro p where p.task.id = ?1")
    void deleteByTaskId(int taskId);
}
