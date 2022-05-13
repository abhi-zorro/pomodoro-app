package com.zemoso.pomodoroapp.controller;

import com.zemoso.pomodoroapp.dao.PomodoroRepository;
import com.zemoso.pomodoroapp.dao.TaskRepository;
import com.zemoso.pomodoroapp.dao.UserRepository;
import com.zemoso.pomodoroapp.entity.Pomodoro;
import com.zemoso.pomodoroapp.entity.Task;
import com.zemoso.pomodoroapp.entity.User;
import com.zemoso.pomodoroapp.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pomodoro")
public class PomoController {

    private User currentUser;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PomodoroRepository pomodoroRepository;

//    private TaskService taskService;
//
//    public PomoController(TaskService taskService){
//        this.taskService = taskService;
//    }

    @GetMapping("/index")
    public String index(){
        return "redirect:/pomodoro/phome";
    }

    @GetMapping("/")
    public String getHome(Model theModel, Authentication authentication){
        this.currentUser = userRepository.findByEmail(authentication.getName());
        return "pomodoro/phome";
    }

    @GetMapping("/createTask")
    public String showTaskForm(Model theModel){
        Task theTask = new Task();
        theModel.addAttribute("task", theTask);
        return "pomodoro/task-form";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute("theTask") Task theTask){
        System.out.println("The task details: " + theTask);
        this.currentUser.addTask(theTask);
        userRepository.save(this.currentUser);
        return "redirect:/pomodoro/viewTasks";
    }

    @GetMapping("/viewTasks")
    public String showAllTasks(Model theModel){
        List<Task> tasks = taskRepository.findByUserId(this.currentUser.getId());
        System.out.println("Tasks size: "+tasks.size());
        theModel.addAttribute("tasks",tasks);
        return "pomodoro/view-tasks";
    }

    @GetMapping("/removeTask")
    public String removeTask(@RequestParam("taskId") int taskId, Model theModel){
        Optional<Task> result = taskRepository.findById(taskId);
        Task task;
        if(result.isPresent()){
            task = result.get();
        }
        else{
            throw new TaskNotFoundException();
        }
        System.out.println("Task to be deleted " + task.getTitle() + task.getId());
        pomodoroRepository.deleteByTaskId(taskId);
        taskRepository.deleteByTaskId(taskId);
        this.currentUser.deleteTask(task);
        userRepository.save(this.currentUser);
        return "redirect:/pomodoro/viewTasks";
    }

    @GetMapping("/startTask")
    public String startTask(@RequestParam("taskId") int taskId, Model theModel){
        //get the current time
        System.out.println("starting time: " + LocalTime.now());

        //get the task
        Optional<Task> result = taskRepository.findById(taskId);
        Task task;
        if(result.isPresent()){
            task = result.get();
        }
        else{
            throw new TaskNotFoundException();
        }

        // check if the task has any pomodoros
        if(task.getPomodoroList().size()>0){
            System.out.println("The pomodoros are : " + task.getPomodoroList().toArray());
        }
        else {
            Pomodoro pomodoro = new Pomodoro();
            pomodoro.setStartTime(Time.valueOf(LocalTime.now()));
            pomodoro.setEndTime(Time.valueOf(LocalTime.now().plusMinutes(25L)));
            pomodoro.setStatus(0);
            pomodoro.setDate(Date.valueOf(LocalDate.now()));
            task.addPomodoro(pomodoro);
            taskRepository.save(task);
        }
        theModel.addAttribute("task", task);
        return "dojo/pomo-mode";
    }

    @GetMapping("/markComplete")
    public String markAsComplete(@RequestParam("taskId") int taskId, Model theModel){
        System.out.println("task id: " + taskId + "user id " + this.currentUser.getId() + this.currentUser.getFirstName());
//        List<Task> totalTasks = taskRepository.findByUserId(this.currentUser.getId());
        List<Task> totalTasks = this.currentUser.getTaskList();
        Task currentTask=null;
        for(Task task: totalTasks){
            System.out.println("Task " + task.getId() );
            if(task.getId()==taskId){
                currentTask = task;
            }
        }
        if(currentTask == null){
            throw new TaskNotFoundException();
        }
//        Optional<Task> currentTask = taskRepository.findById(taskId);
        List<Pomodoro> pomos = pomodoroRepository.findByTaskId(taskId);
        System.out.println("Pomos status: " + pomos.get(0).getStatus());
        for(Pomodoro pomo: pomos) {
            System.out.println("pomo details " + pomo.getId());
            pomo.setStatus(1);
            pomodoroRepository.save(pomo);
           // currentTask.addPomodoro(pomo);
        }
        currentTask.setStatus(true);
        taskRepository.save(currentTask);
        return "redirect:/pomodoro/viewTasks";
    }
}
