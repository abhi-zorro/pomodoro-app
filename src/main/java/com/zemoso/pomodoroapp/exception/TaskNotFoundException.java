package com.zemoso.pomodoroapp.exception;

public class TaskNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
