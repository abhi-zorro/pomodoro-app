package com.zemoso.pomodoroapp.mapservice;

import com.zemoso.pomodoroapp.dto.TaskDto;
import com.zemoso.pomodoroapp.dto.UserDto;
import com.zemoso.pomodoroapp.entity.Task;
import com.zemoso.pomodoroapp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MapperService {
    @Autowired
    private ModelMapper modelMapper;

    public Task convertToEntity(TaskDto taskDto)  {
        try {
            Task task = modelMapper.map(taskDto, Task.class);
            log.info(">>>>> INSIDE MAPPER SERVICE: convertToEntity Tryblock");
            return task;
        }
        catch (NullPointerException ne){
            log.info(">>>>> INSIDE MAPPER SERVICE: NULLPOINTEREXCEPTION catch block");
            throw new NullPointerException("Null pointer");
        }
        finally {
            log.info(">>>>> INSIDE FINALLY ");
        }
    }

    public User convertToEntity(UserDto userDto){
        try{
            User user = modelMapper.map(userDto, User.class);
            log.info(">>>>> INSIDE MAPPER SERVICE: convertToEntity UserDto try block");
            return user;
        }
        catch (NullPointerException ne){
            log.info(">>>>> INSIDE MAPPER SERVICE: convertToEntity UserDto catch block");
            throw new NullPointerException("null pointer in user convertToEntity");
        }
    }
}
