package com.zemoso.pomodoroapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
