package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

public interface UserService {
	
	User SaveUser(User user);
	Role SaveRole(Role role);
	void addRoleToUser(String userName,String roleName);
	User getUser(String username);
	List<User> getUsers();

}
