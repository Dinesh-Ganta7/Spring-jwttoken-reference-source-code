package com.alibou.security.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibou.security.config.JwtService;
import com.alibou.security.dto.UserDTO;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserDTO getUser(String email) {

		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			UserDTO userDto = UserDTO.builder().build();
			BeanUtils.copyProperties(optionalUser.get(), userDto);
			return userDto;

		}
		return null;
	}

}
