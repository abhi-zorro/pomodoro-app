package com.zemoso.pomodoroapp.dto;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class PomodoroDto {

    private int id;

    private Time startTime;

    private Time endTime;

    private Date date;

    private int status;

    private TaskDto task;


    @Override
    public String toString() {
        return "Pomodoro{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", status=" + status +
                ", task=" + task +
                '}';
    }
}
