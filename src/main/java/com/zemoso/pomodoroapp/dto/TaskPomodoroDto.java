package com.zemoso.pomodoroapp.dto;

import com.zemoso.pomodoroapp.entity.Pomodoro;
import com.zemoso.pomodoroapp.entity.Task;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
public class TaskPomodoroDto {
    private Task task;
    private Pomodoro pomodoro;
}
