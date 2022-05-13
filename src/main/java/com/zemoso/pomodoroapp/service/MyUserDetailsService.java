package com.zemoso.pomodoroapp.service;

import com.zemoso.pomodoroapp.dao.UserRepository;
import com.zemoso.pomodoroapp.entity.MyUserPrincipal;
import com.zemoso.pomodoroapp.entity.User;
import com.zemoso.pomodoroapp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        System.out.println("email: "+username + "user details: " + user.getEmail());
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        System.out.println("User pwdd: " + user.getPassword());
        return new MyUserPrincipal(user);
    }
}
