package com.zemoso.pomodoroapp.exception;

import com.zemoso.pomodoroapp.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity<Object> taskNotFound(TaskNotFoundException taskNotFoundException){
        return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
    }
}
