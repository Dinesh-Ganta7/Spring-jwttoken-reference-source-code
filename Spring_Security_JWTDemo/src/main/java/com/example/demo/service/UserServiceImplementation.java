package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleDao;
import com.example.demo.repo.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

	private final UserDao userDao;

	private final RoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in the database: {}", username);
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	@Override
	public User SaveUser(User user) {
		log.info("Saving new user to the database");
		return userDao.save(user);
	}

	@Override
	public Role SaveRole(Role role) {
		log.info("Saving new role {} to the database", role.getName());
		return roleDao.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		log.info("Adding role {} to the user {} to the database", roleName, userName);

		User user = userDao.findByUsername(userName);
		Role role = roleDao.findByName(roleName);
		user.getRoles().add(role);

	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {}", username);
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users..");
		return userDao.findAll();
	}

}
