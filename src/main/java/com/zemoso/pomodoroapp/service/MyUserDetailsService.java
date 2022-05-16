package com.zemoso.pomodoroapp.service;

import com.zemoso.pomodoroapp.dao.UserRepository;
import com.zemoso.pomodoroapp.entity.MyUserPrincipal;
import com.zemoso.pomodoroapp.entity.User;
import com.zemoso.pomodoroapp.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userRepository.findByEmail(username);
            log.info("INSIDE loadUserByUsername method >>>>");
            log.info(">>>> email: " + username + "user details: " + user.getEmail());
            log.info(">>>>> User pwd: " + user.getPassword());
            return new MyUserPrincipal(user);
        }
        catch(NullPointerException ne){
            log.info("INSIDE CATCH EXPRESSION");
            throw new UserNotFoundException(username);
        }
    }
}
