package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@SpringBootApplication
public class SpringSecurityJwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtDemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return passwordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.SaveRole(new Role(0, "Role_user"));
			userService.SaveRole(new Role(0, "Role_Manager"));
			userService.SaveRole(new Role(0, "Role_Admin"));
			userService.SaveRole(new Role(0, "Role_Super_Admin"));

			userService.SaveUser(new User(0, "John Cena", "john", "1234", new ArrayList<Role>()));
			userService.SaveUser(new User(0, "Mark Zukerberg", "mark", "1234", new ArrayList<Role>()));
			userService.SaveUser(new User(0, "Virat Kohli", "Kohli", "1234", new ArrayList<Role>()));
			userService.SaveUser(new User(0, "MS Dhoni", "msd", "1234", new ArrayList<Role>()));

			userService.addRoleToUser("msd", "Role_Manager");
			userService.addRoleToUser("Kohli", "Role_Super_Admin");
			userService.addRoleToUser("mark", "Role_Admin");
			userService.addRoleToUser("msd", "Role_Super_Admin");
			userService.addRoleToUser("john", "Role_user");
			userService.addRoleToUser("Kohli", "Role_Admin");
			userService.addRoleToUser("Kohli", "Role_user");
		};
	}

}
