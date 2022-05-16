package com.zemoso.pomodoroapp.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name="note")
    private String note;

    @Column(name="est_pomos")
    private int estPomodoros;

    @Column(name="status")
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<Pomodoro> pomodoroList;

    public void addPomodoro(Pomodoro pomodoro){
        this.pomodoroList.add(pomodoro);
        pomodoro.setTask(this);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", estPomodoros=" + estPomodoros +
                ", status=" + status +
                ", user=" + user +
                ", pomodoroList=" + pomodoroList +
                '}';
    }
}
