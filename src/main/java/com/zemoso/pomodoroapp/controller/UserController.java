package com.zemoso.pomodoroapp.controller;

import com.zemoso.pomodoroapp.entity.User;
import com.zemoso.pomodoroapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/home")
    public String sayHello(Model theModel) {

//        theModel.addAttribute("theDate", new java.util.Date());

        return "home";
    }

    @GetMapping("/register")
    public String registerUser(Model theModel){
        //add the user to db
        User theUser = new User();
        theModel.addAttribute("user",theUser);
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(theUser.getPassword());
        theUser.setPassword(encodedPassword);
        userService.save(theUser);
        return "redirect:/pomologin";
    }

    @GetMapping("/pomologin")
    public String loginUser(Model theModel){
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "login";
    }

    @GetMapping("/error")
    public String errorPage(){
        return "access-denied";
    }

    @GetMapping("/logout")
    public String displayHome(){
        return "home";
    }
}
