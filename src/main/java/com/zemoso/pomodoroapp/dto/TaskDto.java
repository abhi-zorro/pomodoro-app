package com.zemoso.pomodoroapp.dto;

import com.zemoso.pomodoroapp.entity.Pomodoro;
import com.zemoso.pomodoroapp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskDto {

    private int id;

    private String title;

    private String note;

    private int estPomodoros;

    private boolean status;

    private User user;

    private List<Pomodoro> pomodoroList;

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", estPomodoros=" + estPomodoros +
                ", status=" + status +
                '}';
    }
}
