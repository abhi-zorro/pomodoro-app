package com.zemoso.pomodoroapp;

import com.zemoso.pomodoroapp.controller.DemoController;
import com.zemoso.pomodoroapp.controller.PomoController;
import com.zemoso.pomodoroapp.controller.UserController;
import com.zemoso.pomodoroapp.entity.Pomodoro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PomodoroappApplicationTests {

	//testing the controller package

	@Autowired
	private UserController userController;

	@Autowired
	private DemoController demoController;

	@Autowired
	private PomoController pomoController;

	@Test
	void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
		assertThat(demoController).isNotNull();
		assertThat(pomoController).isNotNull();
	}

}
