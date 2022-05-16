package com.zemoso.pomodoroapp.service;

import com.zemoso.pomodoroapp.dao.UserRepository;
import com.zemoso.pomodoroapp.entity.User;
import com.zemoso.pomodoroapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int userId) {
        Optional<User> result = userRepository.findById(userId);

        User user;

        if(result.isPresent()){
            user = result.get();
        }
        else{
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }
}
